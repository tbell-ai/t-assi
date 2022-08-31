package kr.co.tbell.tassi.service;


import kr.co.tbell.tassi.dto.Response;
import kr.co.tbell.tassi.dto.request.ProjectRequestDto;
import kr.co.tbell.tassi.dto.response.BoardResponseDto;
import kr.co.tbell.tassi.dto.response.ProjectResponseDto;
import kr.co.tbell.tassi.entity.Boards;
import kr.co.tbell.tassi.entity.Contract;
import kr.co.tbell.tassi.entity.Project;
import kr.co.tbell.tassi.entity.Users;
import kr.co.tbell.tassi.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ContractRepository contractRepository;
    private final UsersRepository usersRepository;
    private final BoardsRepository boardsRepository;
    private final Response response;

    public ResponseEntity<?> createProject(ProjectRequestDto.Create data) {

        Users user = usersRepository.getById(data.getUserId());
        Contract contract = contractRepository.getById(data.getContractId());

        Project project = Project.builder()
                .projectLeader(data.getProjectLeader())
                .projectSummary(data.getProjectSummary())
                .projectPlan(data.getProjectPlan())
                .projectServiceType(data.getProjectServiceType())
                .projectTestType(data.getProjectTestType())
                .percent(0)
                .totalDefect(0)
                .remainDefect(0)
                .solveDefect(0)
                .neglectDefect(0)
                .projectBtsLink(data.getProjectBtsLink())
                .projectBtsAccount(data.getProjectBtsAccount())
                .projectBtsPassword(data.getProjectBtsPassword())
                .projectState("대기")
                .user(user)
                .contract(contract)
                .build();

        projectRepository.save(project);

        return response.success("프로젝트 생성에 성공했습니다.");
    }

    public ResponseEntity<?> getProjectListByUser(Long id) {

        List<Project> projects = projectRepository.findByUserIdx(id);
        List<ProjectResponseDto.ProjectListSubData> projectList = new ArrayList<>();

        for(Project project : projects) {
            ProjectResponseDto.ProjectListSubData dto = ProjectResponseDto.ProjectListSubData.builder()
                    .projectId(project.getIdx())
                    .projectLeader(project.getProjectLeader())
                    .projectServiceType(project.getProjectServiceType())
                    .projectTestType(project.getProjectTestType())
                    .contractName(project.getContract().getContractName())
                    .build();

            projectList.add(dto);
        }

        return response.success(projectList);
    }

    public ResponseEntity<?> getProjectList(String projectState, int pageNum) {

        Page<Project> projects = projectRepository.findAllSearch(projectState, PageRequest.of(pageNum, 10, Sort.by("create_date").ascending()));
        List<ProjectResponseDto.ProjectListData> projectList = new ArrayList<>();

        for(Project project : projects) {
            ProjectResponseDto.ProjectListData dto = ProjectResponseDto.ProjectListData.builder()
                    .projectId(project.getIdx())
                    .projectLeader(project.getProjectLeader())
                    .projectServiceType(project.getProjectServiceType())
                    .projectTestType(project.getProjectTestType())
                    .contractName(project.getContract().getContractName())
                    .contractManagerName(project.getContract().getCompany().getCompanyManagerName())
                    .contractStart(project.getContract().getContractStart())
                    .build();

            projectList.add(dto);
        }

        ProjectResponseDto.Paging page = ProjectResponseDto.Paging.builder()
                .pageNumber(projects.getNumber())
                .pageSize(projects.getSize())
                .totalPages(projects.getTotalPages())
                .numberOfElements(projects.getNumberOfElements())
                .prev(projects.hasPrevious())
                .next(projects.hasNext())
                .last(projects.isLast())
                .first(projects.isFirst())
                .empty(projects.isEmpty())
                .build();

        ProjectResponseDto.Project data = ProjectResponseDto.Project.builder().projectData(projectList).paging(page).build();

        return response.success(data);
    }

    public ResponseEntity<?> getProject(Long id) {
        Optional<Project> item = projectRepository.findById(id);

        if(item.isPresent()) {
            Project project = item.get();

            ProjectResponseDto.ProjectData dto = ProjectResponseDto.ProjectData.builder()
                    .projectId(project.getIdx())
                    .contractId(project.getContract().getIdx())
                    .userId(project.getUser().getIdx())
                    .projectCustomerName(project.getUser().getName())
                    .projectCustomerEmail(project.getUser().getEmail())
                    .projectCustomerPhone(project.getUser().getPhone())
                    .projectCustomerCompany(project.getUser().getCompany())
                    .projectContractName(project.getContract().getContractName())
                    .projectContractDay(project.getContract().getContractDay())
                    .lowLevel(project.getContract().getContractLowLevel())
                    .middleLevel(project.getContract().getContractMiddleLevel())
                    .highLevel(project.getContract().getContractHighLevel())
                    .specialLevel(project.getContract().getContractSpecialLevel())
                    .projectResultDocument(project.getContract().getContractResultDocument())
                    .projectResultDelivery(project.getContract().getContractResultDelivery())
                    .projectLeader(project.getProjectLeader())
                    .projectSummary(project.getProjectSummary())
                    .projectPlan(project.getProjectPlan())
                    .projectServiceType(project.getProjectServiceType())
                    .projectTestType(project.getProjectTestType())
                    .projectBtsLink(project.getProjectBtsLink())
                    .projectBtsAccount(project.getProjectBtsAccount())
                    .projectBtsPassword(project.getProjectBtsPassword())
                    .projectPercent(project.getPercent())
                    .projectTotalDefect(project.getTotalDefect())
                    .projectRemainDefect(project.getRemainDefect())
                    .projectSolveDefect(project.getSolveDefect())
                    .projectNeglectDefect(project.getNeglectDefect())
                    .projectState(project.getProjectState())
                    .createDate(project.getCreateDate())
                    .modifiedDate(project.getModifiedDate())
                    .build();

            return response.success(dto);
        }

        return null;
    }

    public ResponseEntity<?> getProjectDashboard(Long id) {
        Optional<Project> item = projectRepository.findById(id);
        Page<Boards> boards = boardsRepository.findByProjectIdx(id, "","", "", "", PageRequest.of(0, 5, Sort.by("create_date").descending()));
        List<BoardResponseDto.BoardListData> boardList = new ArrayList<>();

        for(Boards board : boards) {
            BoardResponseDto.BoardListData dto = BoardResponseDto.BoardListData.builder()
                    .id(board.getIdx())
                    .subject(board.getBoardSubject())
                    .type(board.getBoardType())
                    .createDate(board.getCreateDate())
                    .build();

            boardList.add(dto);
        }

        ProjectResponseDto.Dashboard dashboard = null;
        if(item.isPresent()) {
            Project project = item.get();

            dashboard = ProjectResponseDto.Dashboard.builder()
                    .projectBtsLink(project.getProjectBtsLink())
                    .projectBtsAccount(project.getProjectBtsAccount())
                    .projectBtsPassword(project.getProjectBtsPassword())
                    .projectPercent(project.getPercent())
                    .projectTotalDefect(project.getTotalDefect())
                    .projectRemainDefect(project.getRemainDefect())
                    .projectSolveDefect(project.getSolveDefect())
                    .projectNeglectDefect(project.getNeglectDefect())
                    .build();
        }

        ProjectResponseDto.Dashboards data = ProjectResponseDto.Dashboards.builder()
                .dashboard(dashboard)
                .boardList(boardList)
                .build();

        return response.success(data);
    }

    public ResponseEntity<?> updateProject(ProjectRequestDto.Update data) {

        Users user = usersRepository.getById(data.getUserId());
        Contract contract = contractRepository.getById(data.getContractId());

        Project project = Project.builder()
                .idx(data.getProjectId())
                .user(user)
                .contract(contract)
                .projectLeader(data.getProjectLeader())
                .projectSummary(data.getProjectSummary())
                .projectPlan(data.getProjectPlan())
                .projectServiceType(data.getProjectServiceType())
                .projectTestType(data.getProjectTestType())
                .projectBtsLink(data.getProjectBtsLink())
                .projectBtsAccount(data.getProjectBtsAccount())
                .projectBtsPassword(data.getProjectBtsPassword())
                .percent(data.getProjectPercent())
                .totalDefect(data.getProjectTotalDefect())
                .remainDefect(data.getProjectRemainDefect())
                .solveDefect(data.getProjectSolveDefect())
                .neglectDefect(data.getProjectNeglectDefect())
                .projectState(data.getProjectState())
                .build();

        projectRepository.save(project);

        return response.success("프로젝트 수정에 성공하였습니다.");
    }

    public ResponseEntity<?>  deleteProject(Long id) {
        projectRepository.deleteById(id);

        return response.success("프로젝트 삭제에 성공하였습니다.");
    }
}
