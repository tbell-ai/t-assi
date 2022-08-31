package kr.co.tbell.tassi.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TesterRequestDto {

    @Getter
    @Setter
    public static class Create {

        @NotEmpty(message = "테스터명은 필수 입력값입니다.")
        private String testerName;

        @NotEmpty(message = "테스터 소속은 필수 입력값입니다.")
        private String testerDepartment;

        @NotEmpty(message = "테스터 나이는 필수 입력값입니다.")
        private String testerAge;

        @NotEmpty(message = "테스터 최종학력은 필수 입력값입니다.")
        private String testerFinalSchool;

        @NotEmpty(message = "테스터 경력은 필수 입력값입니다.")
        private String testerFinalCareer;

        @NotEmpty(message = "테스터 강점은 필수 입력값입니다.")
        private String testerStrength;
    }

    @Getter
    @Setter
    public static class Update {

        @NotNull
        private Long id;

        @NotEmpty(message = "테스터명은 필수 입력값입니다.")
        private String testerName;

        @NotEmpty(message = "테스터 소속은 필수 입력값입니다.")
        private String testerDepartment;

        @NotEmpty(message = "테스터 나이는 필수 입력값입니다.")
        private String testerAge;

        @NotEmpty(message = "테스터 최종학력은 필수 입력값입니다.")
        private String testerFinalSchool;

        @NotEmpty(message = "테스터 경력은 필수 입력값입니다.")
        private String testerFinalCareer;

        @NotEmpty(message = "테스터 강점은 필수 입력값입니다.")
        private String testerStrength;
    }
}
