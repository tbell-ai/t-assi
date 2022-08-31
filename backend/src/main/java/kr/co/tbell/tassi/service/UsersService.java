package kr.co.tbell.tassi.service;

import kr.co.tbell.tassi.dto.Response;
import kr.co.tbell.tassi.dto.request.UserRequestDto;
import kr.co.tbell.tassi.dto.response.UserResponseDto;
import kr.co.tbell.tassi.entity.Users;
import kr.co.tbell.tassi.enums.Authority;
import kr.co.tbell.tassi.jwt.JwtTokenProvider;
import kr.co.tbell.tassi.repository.UsersRepository;
import kr.co.tbell.tassi.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

@Slf4j
@RequiredArgsConstructor
@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final Response response;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RedisTemplate redisTemplate;


    public ResponseEntity<?> signUp(UserRequestDto.SignUp signUp) {
        if (usersRepository.existsByEmail(signUp.getEmail())) {
            return response.fail("이미 회원가입된 이메일입니다.", HttpStatus.BAD_REQUEST);
        }

        if(signUp.getRole().equals("ADMIN")) {
            Users user = Users.builder()
                    .email(signUp.getEmail())
                    .password(passwordEncoder.encode(signUp.getPassword()))
                    .name(signUp.getName())
                    .company(signUp.getCompany())
                    .phone(signUp.getPhone())
                    .personalDataYn(signUp.isPersonalDataYn())
                    .termYn(signUp.isTermYn())
                    .roles(Collections.singletonList(Authority.ROLE_ADMIN.name()))
                    .state("특별회원")
                    .build();
            usersRepository.save(user);
        } else {
            Users user = Users.builder()
                    .email(signUp.getEmail())
                    .password(passwordEncoder.encode(signUp.getPassword()))
                    .name(signUp.getName())
                    .company(signUp.getCompany())
                    .phone(signUp.getPhone())
                    .personalDataYn(signUp.isPersonalDataYn())
                    .termYn(signUp.isTermYn())
                    .roles(Collections.singletonList(Authority.ROLE_USER.name()))
                    .state("기본회원")
                    .build();
            usersRepository.save(user);
        }


        return response.success("회원가입에 성공했습니다.");
    }

    public ResponseEntity<?> login(UserRequestDto.Login login) {

        Users user = usersRepository.findByEmail(login.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("No authentication information."));

        if (user == null) {
            return response.fail("해당하는 유저가 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        if(passwordEncoder.matches(login.getPassword(), user.getPassword())) {
            // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
            // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
            UsernamePasswordAuthenticationToken authenticationToken = login.toAuthentication();

            // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
            // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

            // 3. 인증 정보를 기반으로 JWT 토큰 생성
            UserResponseDto.TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication, user);

            // 4. RefreshToken Redis 저장 (expirationTime 설정을 통해 자동 삭제 처리)
            redisTemplate.opsForValue()
                    .set("RT:" + authentication.getName(), tokenInfo.getRefreshToken(), tokenInfo.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);


            return response.success(tokenInfo, "로그인에 성공했습니다.", HttpStatus.OK);
        }

        return response.fail("회원 인증 정보가 잘못되었습니다.", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> getUserList(String name, String email, String state, int page) {

        Page<Users> users = usersRepository.findAllSearch(name, email, state, PageRequest.of(page, 10, Sort.by("create_date").descending()));
        return getResponseEntity(users);
    }

    private ResponseEntity<?> getResponseEntity(Page<Users> users) {
        List<UserResponseDto.UserListData> userList = new ArrayList<>();

        for(Users user : users) {
            UserResponseDto.UserListData dto = UserResponseDto.UserListData.builder()
                    .id(user.getIdx())
                    .email(user.getEmail())
                    .name(user.getName())
                    .state(user.getState())
                    .createDate(user.getCreateDate())
                    .build();

            userList.add(dto);
        }

        UserResponseDto.Paging page = UserResponseDto.Paging.builder()
                .pageNumber(users.getNumber())
                .pageSize(users.getSize())
                .totalPages(users.getTotalPages())
                .numberOfElements(users.getNumberOfElements())
                .prev(users.hasPrevious())
                .next(users.hasNext())
                .last(users.isLast())
                .first(users.isFirst())
                .empty(users.isEmpty())
                .build();

        UserResponseDto.User data = UserResponseDto.User.builder().userData(userList).paging(page).build();

        return response.success(data);
    }

    public ResponseEntity<?> getUser(Long id) {
        Optional<Users> item = usersRepository.findById(id);

        if(item.isPresent()) {
            Users user = item.get();

            UserResponseDto.UserData dto = UserResponseDto.UserData.builder()
                    .id(user.getIdx())
                    .email(user.getEmail())
                    .name(user.getName())
                    .company(user.getCompany())
                    .phone(user.getPhone())
                    .state(user.getState())
                    .role(user.getRoles().get(0))
                    .personDataYn(user.getPersonalDataYn())
                    .termYn(user.getTermYn())
                    .createDate(user.getCreateDate())
                    .modifiedDate(user.getModifiedDate())
                    .build();

            return response.success(dto);
        }

        return null;
    }

    public ResponseEntity<?> updateUser(UserRequestDto.Update data) {

        Optional<Users> item = usersRepository.findByEmail(data.getEmail());

        if(data.getNewPassword().isEmpty() || data.getOldPassword().isEmpty()) {
            if(item.isPresent()) {
                Users user = item.get();

                Users update = Users.builder()
                        .idx(data.getId())
                        .email(data.getEmail())
                        .password(user.getPassword())
                        .name(data.getName())
                        .company(data.getCompany())
                        .phone(data.getPhone())
                        .state(user.getState())
                        .roles(user.getRoles())
                        .personalDataYn(data.isPersonalDataYn())
                        .termYn(data.isTermYn())
                        .build();

                usersRepository.save(update);
            }
        } else {
            if(item.isPresent()) {
                String pattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$$";

                if(Pattern.matches(pattern, data.getOldPassword()) && Pattern.matches(pattern, data.getNewPassword())) {
                    Users user = item.get();

                    if(passwordEncoder.matches(data.getOldPassword(), user.getPassword())) {
                        Users update = Users.builder()
                                .idx(data.getId())
                                .email(data.getEmail())
                                .password(passwordEncoder.encode(data.getNewPassword()))
                                .name(data.getName())
                                .company(data.getCompany())
                                .phone(data.getPhone())
                                .state(user.getState())
                                .personalDataYn(data.isPersonalDataYn())
                                .termYn(data.isTermYn())
                                .roles(user.getRoles())
                                .build();

                        usersRepository.save(update);
                    } else {
                        return response.fail("현재 비밀번호가 맞지 않습니다.", HttpStatus.BAD_REQUEST);
                    }
                } else {
                    return response.fail("비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.", HttpStatus.BAD_REQUEST);
                }

            }
        }

        return response.success("사용자 정보 수정에 성공하였습니다.");
    }

    public ResponseEntity<?> deleteUser(Long id) {
        usersRepository.deleteById(id);

        return response.success("사용자 삭제에 성공하였습니다.");
    }


    public ResponseEntity<?> reissue(UserRequestDto.Reissue reissue) {

        Users user = usersRepository.findByEmail(reissue.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("No authentication information."));

        // 1. Refresh Token 검증
        if (!jwtTokenProvider.validateToken(reissue.getRefreshToken())) {
            return response.fail("Refresh Token 정보가 유효하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        // 2. Access Token 에서 User email 을 가져옵니다.
        Authentication authentication = jwtTokenProvider.getAuthentication(reissue.getAccessToken());

        // 3. Redis 에서 User email 을 기반으로 저장된 Refresh Token 값을 가져옵니다.
        String refreshToken = (String)redisTemplate.opsForValue().get("RT:" + authentication.getName());
        // (추가) 로그아웃되어 Redis 에 RefreshToken 이 존재하지 않는 경우 처리
        if(ObjectUtils.isEmpty(refreshToken)) {
            return response.fail("잘못된 요청입니다.", HttpStatus.BAD_REQUEST);
        }
        if(!refreshToken.equals(reissue.getRefreshToken())) {
            return response.fail("Refresh Token 정보가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        // 4. 새로운 토큰 생성(redis에 email 기반 저장된 Refresh Token 값이 있으므로, email과 name 정보를 그대로 리턴)
        UserResponseDto.TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication, user);

        // 5. RefreshToken Redis 업데이트
        redisTemplate.opsForValue()
                .set("RT:" + authentication.getName(), tokenInfo.getRefreshToken(), tokenInfo.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);

        return response.success(tokenInfo, "Token 정보가 갱신되었습니다.", HttpStatus.OK);
    }

    public ResponseEntity<?> logout(UserRequestDto.Logout logout) {
        // 1. Access Token 검증
        if (!jwtTokenProvider.validateToken(logout.getAccessToken())) {
            return response.fail("잘못된 요청입니다.", HttpStatus.BAD_REQUEST);
        }

        // 2. Access Token 에서 User email 을 가져옵니다.
        Authentication authentication = jwtTokenProvider.getAuthentication(logout.getAccessToken());

        // 3. Redis 에서 해당 User email 로 저장된 Refresh Token 이 있는지 여부를 확인 후 있을 경우 삭제합니다.
        if (redisTemplate.opsForValue().get("RT:" + authentication.getName()) != null) {
            // Refresh Token 삭제
            redisTemplate.delete("RT:" + authentication.getName());
        }

        // 4. 해당 Access Token 유효시간 가지고 와서 BlackList 로 저장하기
        Long expiration = jwtTokenProvider.getExpiration(logout.getAccessToken());
        redisTemplate.opsForValue()
                .set(logout.getAccessToken(), "logout", expiration, TimeUnit.MILLISECONDS);

        return response.success("로그아웃 되었습니다.");
    }

    public ResponseEntity<?> authority() {
        // SecurityContext에 담겨 있는 authentication userEamil 정보
        String userEmail = SecurityUtil.getCurrentUserEmail();

        Users user = usersRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("No authentication information."));

        // add ROLE_ADMIN
        user.getRoles().add(Authority.ROLE_ADMIN.name());
        usersRepository.save(user);

        return response.success();
    }
}
