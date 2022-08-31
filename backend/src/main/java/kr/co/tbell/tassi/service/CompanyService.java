package kr.co.tbell.tassi.service;

import kr.co.tbell.tassi.dto.Response;
import kr.co.tbell.tassi.dto.request.CompanyRequestDto;
import kr.co.tbell.tassi.dto.response.CompanyResponseDto;
import kr.co.tbell.tassi.entity.Company;
import kr.co.tbell.tassi.entity.Users;
import kr.co.tbell.tassi.repository.CompanyRepository;
import kr.co.tbell.tassi.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final UsersRepository usersRepository;
    private final Response response;

    public ResponseEntity<?> createCompany(CompanyRequestDto.Create data) {

        Users user = usersRepository.getById(data.getUserId());

        Company company = Company.builder()
                .companyName(data.getName())
                .companyAddress(data.getAddress())
                .companyCeoName(data.getCeoName())
                .companyManagerName(data.getManagerName())
                .companyPhone(data.getPhone())
                .companyEmail(data.getEmail())
                .companyManagerPhone(data.getManagerPhone())
                .companyManagerEmail(data.getManagerEmail())
                .companyState("업체 등록")
                .user(user)
                .build();

        companyRepository.save(company);

        return response.success("업체 생성에 성공했습니다.");
    }

    public ResponseEntity<?> getCompanyList(String companyName, String companyCeoName, String companyManagerName, int pageNum) {

        Page<Company> companies = companyRepository.findAllSearch(companyName, companyCeoName, companyManagerName, PageRequest.of(pageNum, 10, Sort.by("create_date").descending()));
        List<CompanyResponseDto.CompanyListData> companyList = new ArrayList<>();

        for(Company company : companies) {

            CompanyResponseDto.CompanyListData dto = CompanyResponseDto.CompanyListData.builder()
                    .companyId(company.getIdx())
                    .companyName(company.getCompanyName())
                    .companyCeoName(company.getCompanyCeoName())
                    .companyManagerName(company.getCompanyManagerName())
                    .userName(company.getUser().getName())
                    .createDate(company.getCreateDate())
                    .build();

            companyList.add(dto);
        }

        CompanyResponseDto.Paging page = CompanyResponseDto.Paging.builder()
                .pageNumber(companies.getNumber())
                .pageSize(companies.getSize())
                .totalPages(companies.getTotalPages())
                .numberOfElements(companies.getNumberOfElements())
                .prev(companies.hasPrevious())
                .next(companies.hasNext())
                .last(companies.isLast())
                .first(companies.isFirst())
                .empty(companies.isEmpty())
                .build();

        CompanyResponseDto.Company data = CompanyResponseDto.Company.builder().companyData(companyList).paging(page).build();

        return response.success(data);
    }

    public ResponseEntity<?> getCompany(Long id) {
        Optional<Company> item = companyRepository.findById(id);

        if(item.isPresent()) {
            Company company = item.get();

            CompanyResponseDto.CompanyData dto = CompanyResponseDto.CompanyData.builder()
                    .id(company.getIdx())
                    .name(company.getCompanyName())
                    .address(company.getCompanyAddress())
                    .ceoName(company.getCompanyCeoName())
                    .managerName(company.getCompanyManagerName())
                    .phone(company.getCompanyPhone())
                    .email(company.getCompanyEmail())
                    .managerPhone(company.getCompanyManagerPhone())
                    .managerEmail(company.getCompanyManagerEmail())
                    .state(company.getCompanyState())
                    .writer(company.getUser().getName())
                    .userId(company.getUser().getIdx())
                    .createDate(company.getCreateDate())
                    .modifiedDate(company.getModifiedDate())
                    .build();

            return response.success(dto);
        }

        return null;
    }

    public ResponseEntity<?> updateCompany(CompanyRequestDto.Update data) {

        Users user = usersRepository.getById(data.getUserId());

        Company company = Company.builder()
                .idx(data.getId())
                .companyName(data.getName())
                .companyAddress(data.getAddress())
                .companyCeoName(data.getCeoName())
                .companyManagerName(data.getManagerName())
                .companyPhone(data.getPhone())
                .companyEmail(data.getEmail())
                .companyManagerPhone(data.getManagerPhone())
                .companyManagerEmail(data.getManagerEmail())
                .companyState(data.getState())
                .user(user)
                .build();

        companyRepository.save(company);

        return response.success("업체 수정에 성공하였습니다.");
    }

    public ResponseEntity<?> deleteCompany(Long id) {
        companyRepository.deleteById(id);

        return response.success("업체 삭제에 성공하였습니다.");
    }

}
