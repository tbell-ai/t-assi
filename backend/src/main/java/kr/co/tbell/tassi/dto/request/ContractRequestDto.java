package kr.co.tbell.tassi.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ContractRequestDto {

    @Getter
    @Setter
    public static class Create {

        @NotEmpty(message = "계약명은 필수 입력값입니다.")
        private String contractName;

        @NotNull
        private int contractCharge;

        @NotEmpty(message = "실제 계약일은 필수 입력값입니다.")
        private String contractDay;

        @NotEmpty(message = "계약 시작일은 필수 입력값입니다.")
        private String contractStart;

        @NotEmpty(message = "계약 종료일은 필수 입력값입니다.")
        private String contractEnd;

        @NotEmpty(message = "계약 내용은 필수 입력값입니다.")
        private String contractContent;

        @NotNull
        private int lowLevel;

        @NotNull
        private int middleLevel;

        @NotNull
        private int highLevel;

        @NotNull
        private int specialLevel;

        @NotEmpty(message = "최종산출물은 필수 입력값입니다.")
        private String resultDocument;

        @NotEmpty(message = "산출물 전달방법은 필수 입력값입니다.")
        private String resultDelivery;

        @NotNull
        private Long companyId;

        @NotNull
        private Long userId;
    }

    @Getter
    @Setter
    public static class Update {

        @NotNull
        private Long id;

        @NotEmpty(message = "계약명은 필수 입력값입니다.")
        private String contractName;

        @NotNull
        private int contractCharge;

        @NotEmpty(message = "실제 계약일은 필수 입력값입니다.")
        private String contractDay;

        @NotEmpty(message = "계약 시작일은 필수 입력값입니다.")
        private String contractStart;

        @NotEmpty(message = "계약 종료일은 필수 입력값입니다.")
        private String contractEnd;

        @NotEmpty(message = "계약 내용은 필수 입력값입니다.")
        private String contractContent;

        @NotNull
        private int lowLevel;

        @NotNull
        private int middleLevel;

        @NotNull
        private int highLevel;

        @NotNull
        private int specialLevel;

        @NotEmpty(message = "최종산출물은 필수 입력값입니다.")
        private String resultDocument;

        @NotEmpty(message = "산출물 전달방법은 필수 입력값입니다.")
        private String resultDelivery;

        @NotEmpty(message = "계약 상태는 필수 입력값입니다.")
        private String contractState;

        @NotNull
        private Long companyId;

        @NotNull
        private Long userId;
    }
}
