package kr.co.tbell.tassi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class UserResponseDto {

    @Builder
    @Getter
    @AllArgsConstructor
    public static class TokenInfo {
        private Long id;
        private String email;
        private String name;
        private String company;
        private String role;
        private String grantType;
        private String accessToken;
        private String refreshToken;
        private Long refreshTokenExpirationTime;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class UserData {
        private Long id;
        private String email;
        private String name;
        private String company;
        private String phone;
        private String state;
        private String role;
        private Boolean personDataYn;
        private Boolean termYn;
        private LocalDateTime createDate;
        private LocalDateTime modifiedDate;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class UserListData {
        private Long id;
        private String email;
        private String name;
        private String state;
        private LocalDateTime createDate;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class Paging {
        // 현재 페이지 크기
        private Integer pageSize;

        // 현재 페이지 정보
        private Integer pageNumber;

        // 전체 페이지 수
        private Integer totalPages;

        // 결과 데이터 수
        private Integer numberOfElements;

        // 마지막 페이지 여부
        private boolean last;

        // 첫번째 페이지 여부
        private boolean first;

        // 빈 페이지 여부
        private boolean empty;

        // 이전 페이지 존재 여부
        private boolean prev;

        // 다음 페이지 존재 여부
        private boolean next;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class User {
        private List<UserListData> userData;
        private Paging paging;
    }

}
