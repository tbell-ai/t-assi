package kr.co.tbell.tassi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class CompanyResponseDto {

    @Builder
    @Getter
    @AllArgsConstructor
    public static class CompanyId {
        private Long id;
    }


    @Builder
    @Getter
    @AllArgsConstructor
    public static class CompanyData {
        private Long id;
        private Long userId;
        private String name;
        private String address;
        private String ceoName;
        private String managerName;
        private String phone;
        private String email;
        private String managerPhone;
        private String managerEmail;
        private String state;
        private String writer;
        private LocalDateTime createDate;
        private LocalDateTime modifiedDate;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class CompanyListData {
        private Long companyId;
        private String companyName;
        private String companyCeoName;
        private String companyManagerName;
        private String userName;
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
    public static class Company {
        private List<CompanyResponseDto.CompanyListData> companyData;
        private CompanyResponseDto.Paging paging;
    }
}
