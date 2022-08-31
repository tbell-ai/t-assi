package kr.co.tbell.tassi.controller;

import kr.co.tbell.tassi.dto.Response;
import kr.co.tbell.tassi.dto.request.CompanyRequestDto;
import kr.co.tbell.tassi.lib.Helper;
import kr.co.tbell.tassi.service.CompanyService;
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
public class CompanyController {

    private final CompanyService companyService;
    private final Response response;

    @PostMapping("company")
    public ResponseEntity<?> create(@RequestBody @Validated CompanyRequestDto.Create company, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }

        return companyService.createCompany(company);
    }

    @GetMapping("company")
    public ResponseEntity<?> list(
            @RequestParam String companyName,
            @RequestParam String companyCeoName,
            @RequestParam String companyManagerName,
            @RequestParam int page
    ) {
        return companyService.getCompanyList(companyName, companyCeoName, companyManagerName, page);
    }

    @GetMapping("company/{id}")
    public ResponseEntity<?> view(@PathVariable("id") Long idx) {
        return companyService.getCompany(idx);
    }

    @PatchMapping("company")
    public ResponseEntity<?> update(@RequestBody @Validated CompanyRequestDto.Update company, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }

        return companyService.updateCompany(company);
    }

    @DeleteMapping("company/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long idx) {
        return companyService.deleteCompany(idx);
    }
}
