package kr.co.tbell.tassi.controller;

import kr.co.tbell.tassi.dto.Response;
import kr.co.tbell.tassi.dto.request.BoardRequestDto;
import kr.co.tbell.tassi.dto.request.CommentRequestDto;
import kr.co.tbell.tassi.lib.Helper;
import kr.co.tbell.tassi.service.BoardsService;
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
public class BoardController {

    private final BoardsService boardsService;
    private final Response response;

    @PostMapping("boards")
    public ResponseEntity<?> create(@RequestBody @Validated BoardRequestDto.Create board, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }

        return boardsService.createBoard(board);
    }

    @PostMapping("/boards/comment")
    public ResponseEntity<?> createComment(@RequestBody @Validated CommentRequestDto.Create comment, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }

        return boardsService.createComment(comment);
    }

    @GetMapping("boards")
    public ResponseEntity<?> list(
            @RequestParam String boardState,
            @RequestParam String boardType,
            @RequestParam String boardSubject,
            @RequestParam String boardContent,
            @RequestParam int page) {
        return boardsService.getBoardList(boardState, boardType, boardSubject, boardContent, page);
    }

    @GetMapping("/boards/comment/{boardId}")
    public ResponseEntity<?> commentList(@PathVariable("boardId") Long boardId) {
        return boardsService.getCommentList(boardId);
    }

    @GetMapping("boards/project")
    public ResponseEntity<?> getListByProject(
            @RequestParam Long id,
            @RequestParam String boardState,
            @RequestParam String boardType,
            @RequestParam String boardSubject,
            @RequestParam String boardContent,
            @RequestParam int page) {
        return boardsService.getBoardListByProject(id, boardState, boardType, boardSubject, boardContent, page);
    }

    @GetMapping("boards/{id}")
    public ResponseEntity<?> view(@PathVariable("id") Long idx) {
        return boardsService.getBoard(idx);
    }

    @PatchMapping("boards")
    public ResponseEntity<?> update(@RequestBody @Validated BoardRequestDto.Update board, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }

        return boardsService.updateBoard(board);
    }

    @DeleteMapping("boards/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long idx) {
        return boardsService.deleteBoard(idx);
    }


}
