package kr.co.tbell.tassi.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ContactRequestDto {

    @Getter
    @Setter
    public static class Create {

        @NotEmpty(message = "의뢰 서비스 명칭은 필수 입력값입니다.")
        private String contactServiceName;

        @NotEmpty(message = "의뢰 서비스 유형은 필수 입력값입니다.")
        private String contactServiceType;

        @NotEmpty(message = "테스트 유형은 필수 입력값입니다.")
        private String contactTestType;

        @NotEmpty(message = "검증 시작일은 필수 입력값입니다.")
        private String contactStart;

        @NotEmpty(message = "검증 종료일은 필수 입력값입니다.")
        private String contactEnd;

        @NotEmpty(message = "검증 내용은 필수 입력값입니다.")
        private String contactContent;

        @NotNull
        private Long userId;
    }

    @Getter
    @Setter
    public static class Update {

        @NotNull
        private Long contactId;

        @NotEmpty(message = "의뢰 서비스 명칭은 필수 입력값입니다.")
        private String contactServiceName;

        @NotEmpty(message = "의뢰 서비스 유형은 필수 입력값입니다.")
        private String contactServiceType;

        @NotEmpty(message = "테스트 유형은 필수 입력값입니다.")
        private String contactTestType;

        @NotEmpty(message = "검증 시작일은 필수 입력값입니다.")
        private String contactStart;

        @NotEmpty(message = "검증 종료일은 필수 입력값입니다.")
        private String contactEnd;

        @NotEmpty(message = "검증 내용은 필수 입력값입니다.")
        private String contactContent;

        @NotEmpty(message = "검증 상태는 필수 입력값입니다.")
        private String contactState;

        @NotNull
        private Long userId;
    }
}
