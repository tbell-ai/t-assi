package kr.co.tbell.tassi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/tester")
@RestController
public class TesterController {
//
//    private final TesterService testerService;
//    private final Response response;
//
//    @PostMapping("/create")
//    public ResponseEntity<?> create(@Validated TesterRequestDto.Create tester, Errors errors) {
//        // validation check
//        if (errors.hasErrors()) {
//            return response.invalidFields(Helper.refineErrors(errors));
//        }
//
//        return testerService.createTester(tester);
//    }
//
//    @GetMapping("/list")
//    public ResponseEntity<?> list() {
//        return testerService.getTesterList();
//    }
//
//    @GetMapping("/view/{id}")
//    public ResponseEntity<?> view(@PathVariable("id") Long idx) {
//        return testerService.getTester(idx);
//    }
//
//    @PatchMapping("/update")
//    public ResponseEntity<?> update(@Validated TesterRequestDto.Update tester, Errors errors) {
//        // validation check
//        if (errors.hasErrors()) {
//            return response.invalidFields(Helper.refineErrors(errors));
//        }
//
//        return testerService.updateTester(tester);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> delete(@PathVariable("id") Long idx) {
//        return testerService.deleteTester(idx);
//    }
}
