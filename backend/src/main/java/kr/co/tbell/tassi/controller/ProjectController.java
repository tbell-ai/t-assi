package kr.co.tbell.tassi.controller;

import kr.co.tbell.tassi.dto.Response;
import kr.co.tbell.tassi.dto.request.ProjectRequestDto;
import kr.co.tbell.tassi.lib.Helper;
import kr.co.tbell.tassi.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api")
@RestController
public class ProjectController {

    private final ProjectService projectService;
    private final Response response;

    @PostMapping("/project")
    public ResponseEntity<?> create(@RequestBody @Validated ProjectRequestDto.Create project, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }

        return projectService.createProject(project);
    }

    @GetMapping("/project")
    public ResponseEntity<?> list(
            @RequestParam String projectState,
            @RequestParam int page) {
        return projectService.getProjectList(projectState, page);
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<?> view(@PathVariable("id") Long idx) {
        return projectService.getProject(idx);
    }

    @GetMapping("/project/dashboard/{id}")
    public ResponseEntity<?> getDash(@PathVariable("id") Long idx) {
        return projectService.getProjectDashboard(idx);
    }

    @GetMapping("/project/list/{id}")
    public ResponseEntity<?> getProjectByUser(@PathVariable("id") Long idx) {
        return projectService.getProjectListByUser(idx);
    }

    @PatchMapping("/project")
    public ResponseEntity<?> update(@RequestBody @Validated ProjectRequestDto.Update project, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }

        return projectService.updateProject(project);
    }

    @DeleteMapping("/project/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long idx) {
        return projectService.deleteProject(idx);
    }
}
