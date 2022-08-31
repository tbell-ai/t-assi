package kr.co.tbell.tassi.controller;

import kr.co.tbell.tassi.dto.Response;
import kr.co.tbell.tassi.dto.request.CommentRequestDto;
import kr.co.tbell.tassi.dto.request.ContactRequestDto;
import kr.co.tbell.tassi.lib.Helper;
import kr.co.tbell.tassi.service.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/")
@RestController
public class ContactController {

    private final ContactService contactService;
    private final Response response;

    @PostMapping("/contact")
    public ResponseEntity<?> create(@RequestBody @Validated ContactRequestDto.Create contact, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }

        return contactService.createContact(contact);
    }

    @PostMapping("/contact/comment")
    public ResponseEntity<?> createComment(@RequestBody @Validated CommentRequestDto.Create comment, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }

        return contactService.createComment(comment);
    }

    @GetMapping("/contact/{userId}")
    public ResponseEntity<?> list(@PathVariable("userId") Long userId) {
        return contactService.getContactList(userId);
    }

    @GetMapping("/contact/comment/{contactId}")
    public ResponseEntity<?> commentList(@PathVariable("contactId") Long contactId) {
        return contactService.getCommentList(contactId);
    }

    @GetMapping("/contact/list")
    public ResponseEntity<?> contactList(
            @RequestParam String serviceName,
            @RequestParam String testType,
            @RequestParam String serviceType,
            @RequestParam String state,
            @RequestParam int page) {

        return contactService.getAdminContactList(serviceName, testType, serviceType, state, page);
    }

    @GetMapping("/contact")
    public ResponseEntity<?> view(@RequestParam(value="contactId", required=true) Long contactId) {
        return contactService.getContact(contactId);
    }

    @PatchMapping("/contact")
    public ResponseEntity<?> update(@RequestBody @Validated ContactRequestDto.Update contact, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }

        return contactService.updateContact(contact);
    }

    @PatchMapping("/contact/comment")
    public ResponseEntity<?> update(@RequestBody @Validated CommentRequestDto.Update comment, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }

        return contactService.updateComment(comment);
    }

    @PatchMapping("/contact/state")
    public ResponseEntity<?> updateContactState(
            @RequestParam Long id,
            @RequestParam String state) {

        return contactService.updateContactState(id, state);
    }

    @GetMapping("/contact/state/{contactId}")
    public ResponseEntity<?> getState(@PathVariable("contactId") Long id) {
        return contactService.getContactState(id);
    }

    @DeleteMapping("/contact/{contactId}")
    public ResponseEntity<?> delete(@PathVariable("contactId") Long id) {
        return contactService.deleteContact(id);
    }
}
