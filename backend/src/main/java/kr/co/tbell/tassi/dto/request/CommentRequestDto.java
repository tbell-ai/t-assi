package kr.co.tbell.tassi.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CommentRequestDto {

    @Getter
    @Setter
    public static class Create {

        @NotNull
        private Long targetId;

        @NotNull
        private Long userId;

        @NotEmpty(message = "댓글 내용은 필수 입력값입니다.")
        private String comment;
    }

    @Getter
    @Setter
    public static class Update {

        @NotNull
        private Long id;

        @NotNull
        private Long userId;

        @NotNull
        private Long targetId;

        @NotEmpty(message = "댓글 내용은 필수 입력값입니다.")
        private String comment;
    }
}
