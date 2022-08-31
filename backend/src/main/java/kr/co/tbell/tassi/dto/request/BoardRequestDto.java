package kr.co.tbell.tassi.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BoardRequestDto {

    @Getter
    @Setter
    public static class Create {

        @NotNull
        private Long userId;

        @NotNull
        private Long projectId;

        @NotEmpty(message = "문의 유형은 필수 입력값입니다.")
        private String type;

        @NotEmpty(message = "문의 제목은 필수 입력값입니다.")
        private String subject;

        @NotEmpty(message = "문의 내용은 필수 입력값입니다.")
        private String content;
    }

    @Getter
    @Setter
    public static class Update {

        @NotNull
        private Long id;

        @NotNull
        private Long userId;

        @NotNull
        private Long projectId;

        @NotEmpty(message = "문의 유형은 필수 입력값입니다.")
        private String type;

        @NotEmpty(message = "문의 제목은 필수 입력값입니다.")
        private String subject;

        @NotEmpty(message = "문의 내용은 필수 입력값입니다.")
        private String content;

        @NotEmpty(message = "문의 상태는 필수 입력값입니다.")
        private String state;
    }
}
