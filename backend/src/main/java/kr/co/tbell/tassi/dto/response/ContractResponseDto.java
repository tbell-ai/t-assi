package kr.co.tbell.tassi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.List;

public class ContractResponseDto {


    @Builder
    @Getter
    @AllArgsConstructor
    public static class ContractId {
        private Long id;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class ContractData {
        private Long contractId;
        private Long companyId;
        private Long userId;
        private String contractName;
        private int contractCharge;
        private String contractDay;
        private String contractStart;
        private String contractEnd;
        private String contractContent;
        private int lowLevel;
        private int middleLevel;
        private int highLevel;
        private int specialLevel;
        private String resultDelivery;
        private String resultDocument;
        private String companyManagerName;
        private String companyManagerPhone;
        private String contractState;
        private LocalDateTime createDate;
        private LocalDateTime modifiedDate;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class ContractListData {
        private Long contactId;
        private Long userId;
        private String contractName;
        private int contractCharge;
        private String companyName;
        private String companyManagerName;
        private String contractDay;
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
    public static class Contract {
        private List<ContractResponseDto.ContractListData> contractData;
        private ContractResponseDto.Paging paging;
    }
}
