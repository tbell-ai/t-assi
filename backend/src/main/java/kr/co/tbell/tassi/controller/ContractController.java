package kr.co.tbell.tassi.controller;

import kr.co.tbell.tassi.dto.Response;
import kr.co.tbell.tassi.dto.request.ContractRequestDto;
import kr.co.tbell.tassi.lib.Helper;
import kr.co.tbell.tassi.service.ContractService;
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
public class ContractController {

    private final ContractService contractService;
    private final Response response;

    @PostMapping("/contract")
    public ResponseEntity<?> create(@RequestBody @Validated ContractRequestDto.Create contract, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }

        return contractService.createContract(contract);
    }

    @GetMapping("/contract")
    public ResponseEntity<?> list(
            @RequestParam String contractName,
            @RequestParam String contractState,
            @RequestParam int page) {
        return contractService.getContractList(contractName, contractState, page);
    }

    @GetMapping("/contract/{id}")
    public ResponseEntity<?> view(@PathVariable("id") Long idx) {
        return contractService.getContract(idx);
    }

    @PatchMapping("/contract")
    public ResponseEntity<?> update(@RequestBody @Validated ContractRequestDto.Update contract, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }

        return contractService.updateContract(contract);
    }

    @DeleteMapping("/contract/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long idx) {
        return contractService.deleteContract(idx);
    }
}
