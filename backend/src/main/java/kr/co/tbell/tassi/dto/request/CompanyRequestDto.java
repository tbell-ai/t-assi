package kr.co.tbell.tassi.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CompanyRequestDto {

    @Getter
    @Setter
    public static class Create {

        @NotEmpty(message = "업체명은 필수 입력값입니다.")
        private String name;

        @NotEmpty(message = "업체주소는 필수 입력값입니다.")
        private String address;

        @NotEmpty(message = "대표자명은 필수 입력값입니다.")
        private String ceoName;

        @NotEmpty(message = "담당자명은 필수 입력값입니다.")
        private String managerName;

        @NotEmpty(message = "업체 대표번호는 필수 입력값입니다.")
        private String phone;

        @NotEmpty(message = "업체 대표번호는 필수 입력값입니다.")
        private String email;

        @NotEmpty(message = "담당자 연락처는 필수 입력값입니다.")
        private String managerPhone;

        @NotEmpty(message = "담당자 이메일은 필수 입력값입니다.")
        private String managerEmail;

        @NotNull
        private Long userId;
    }

    @Getter
    @Setter
    public static class Update {

        @NotNull
        private Long id;

        @NotEmpty(message = "업체명은 필수 입력값입니다.")
        private String name;

        @NotEmpty(message = "업체주소는 필수 입력값입니다.")
        private String address;

        @NotEmpty(message = "대표자명은 필수 입력값입니다.")
        private String ceoName;

        @NotEmpty(message = "담당자명은 필수 입력값입니다.")
        private String managerName;

        @NotEmpty(message = "업체 대표번호는 필수 입력값입니다.")
        private String phone;

        @NotEmpty(message = "업체 대표메일은 필수 입력값입니다.")
        private String email;

        @NotEmpty(message = "담당자 연락처는 필수 입력값입니다.")
        private String managerPhone;

        @NotEmpty(message = "담당자 이메일은 필수 입력값입니다.")
        private String managerEmail;

        @NotEmpty(message = "진행상태는 필수 입력값입니다.")
        private String state;

        @NotNull
        private Long userId;
    }

}
