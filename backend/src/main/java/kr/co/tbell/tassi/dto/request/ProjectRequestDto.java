package kr.co.tbell.tassi.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProjectRequestDto {

    @Getter
    @Setter
    public static class Create {

        @NotNull
        private Long contractId;

        @NotNull
        private Long userId;

        @NotEmpty(message = "프로젝트 리더는 필수 입력값입니다.")
        private String projectLeader;

        @NotEmpty(message = "프로젝트 요약은 필수 입력값입니다.")
        private String projectSummary;

        @NotEmpty(message = "프로젝트 수행계획은 필수 입력값입니다.")
        private String projectPlan;

        @NotEmpty(message = "프로젝트 서비스 유형은 필수 입력값입니다.")
        private String projectServiceType;

        @NotEmpty(message = "프로젝트 테스트 유형은 필수 입력값입니다.")
        private String projectTestType;

        @NotEmpty(message = "Bts Jira Link는 필수 입력값입니다.")
        private String projectBtsLink;

        @NotEmpty(message = "Bts Jira 계정 정보는 필수 입력값입니다.")
        private String projectBtsAccount;

        @NotEmpty(message = "Bts Jira 계정 비밀번호는 필수 입력값입니다.")
        private String projectBtsPassword;
    }

    @Getter
    @Setter
    public static class Update {

        @NotNull
        private Long projectId;

        @NotNull
        private Long contractId;

        @NotNull
        private Long userId;

        @NotEmpty(message = "프로젝트 리더 필수 입력값입니다.")
        private String projectLeader;

        @NotEmpty(message = "프로젝트 요약 필수 입력값입니다.")
        private String projectSummary;

        @NotEmpty(message = "수행계획은 필수 입력값입니다.")
        private String projectPlan;

        @NotEmpty(message = "프로젝트 서비스 유형은 필수 입력값입니다.")
        private String projectServiceType;

        @NotEmpty(message = "프로젝트 테스트 유형은 필수 입력값입니다.")
        private String projectTestType;

        @NotEmpty(message = "Bts Jira Link는 필수 입력값입니다.")
        private String projectBtsLink;

        @NotEmpty(message = "Bts Jira 계정 정보는 필수 입력값입니다.")
        private String projectBtsAccount;

        @NotEmpty(message = "Bts Jira 계정 비밀번호는 필수 입력값입니다.")
        private String projectBtsPassword;

        @NotNull
        private int projectPercent;

        @NotNull
        private int projectTotalDefect;

        @NotNull
        private int projectRemainDefect;

        @NotNull
        private int projectSolveDefect;

        @NotNull
        private int projectNeglectDefect;

        @NotEmpty(message = "프로젝트 상태는 필수 입력값입니다.")
        private String projectState;
    }
}
