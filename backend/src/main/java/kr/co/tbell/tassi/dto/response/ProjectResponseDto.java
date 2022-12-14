package kr.co.tbell.tassi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class ProjectResponseDto {

    @Builder
    @Getter
    @AllArgsConstructor
    public static class ProjectId {
        private Long id;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class ProjectData {
        private Long projectId;
        private Long userId;
        private Long contractId;
        private String projectCustomerName;
        private String projectCustomerEmail;
        private String projectCustomerPhone;
        private String projectCustomerCompany;
        private String projectContractName;
        private String projectContractDay;
        private int lowLevel;
        private int middleLevel;
        private int highLevel;
        private int specialLevel;
        private String projectResultDocument;
        private String projectResultDelivery;
        private String projectLeader;
        private String projectSummary;
        private String projectPlan;
        private String projectServiceType;
        private String projectTestType;
        private String projectBtsLink;
        private String projectBtsAccount;
        private String projectBtsPassword;
        private int projectPercent;
        private int projectTotalDefect;
        private int projectRemainDefect;
        private int projectSolveDefect;
        private int projectNeglectDefect;
        private String projectState;
        private LocalDateTime createDate;
        private LocalDateTime modifiedDate;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class ProjectDashboardData {
        private int projectPercent;
        private int projectTotalDefect;
        private int projectRemainDefect;
        private int projectSolveDefect;
        private int projectNeglectDefect;
        private String projectBtsLink;
        private String projectBtsAccount;
        private String projectBtsPassword;
        private List<BoardResponseDto.BoardData> boardList;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class ProjectListData {
        private Long projectId;
        private String contractName;
        private String contractManagerName;
        private String projectLeader;
        private String projectServiceType;
        private String projectTestType;
        private String contractStart;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class ProjectListSubData {
        private Long projectId;
        private String contractName;
        private String projectLeader;
        private String projectServiceType;
        private String projectTestType;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class Paging {
        // ?????? ????????? ??????
        private Integer pageSize;

        // ?????? ????????? ??????
        private Integer pageNumber;

        // ?????? ????????? ???
        private Integer totalPages;

        // ?????? ????????? ???
        private Integer numberOfElements;

        // ????????? ????????? ??????
        private boolean last;

        // ????????? ????????? ??????
        private boolean first;

        // ??? ????????? ??????
        private boolean empty;

        // ?????? ????????? ?????? ??????
        private boolean prev;

        // ?????? ????????? ?????? ??????
        private boolean next;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class Dashboard {
        private int projectPercent;
        private int projectTotalDefect;
        private int projectRemainDefect;
        private int projectSolveDefect;
        private int projectNeglectDefect;
        private String projectBtsLink;
        private String projectBtsAccount;
        private String projectBtsPassword;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class Project {
        private List<ProjectResponseDto.ProjectListData> projectData;
        private ProjectResponseDto.Paging paging;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class Dashboards {
        private ProjectResponseDto.Dashboard dashboard;
        private List<BoardResponseDto.BoardListData> boardList;
    }

}
