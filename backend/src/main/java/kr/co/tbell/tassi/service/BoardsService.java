package kr.co.tbell.tassi.service;

import kr.co.tbell.tassi.dto.Response;
import kr.co.tbell.tassi.dto.request.BoardRequestDto;
import kr.co.tbell.tassi.dto.request.CommentRequestDto;
import kr.co.tbell.tassi.dto.response.BoardResponseDto;
import kr.co.tbell.tassi.dto.response.CommentResponseDto;
import kr.co.tbell.tassi.entity.*;
import kr.co.tbell.tassi.repository.BoardCommentRepository;
import kr.co.tbell.tassi.repository.BoardsRepository;
import kr.co.tbell.tassi.repository.ProjectRepository;
import kr.co.tbell.tassi.repository.UsersRepository;
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
public class BoardsService {

    private final ProjectRepository projectRepository;
    private final UsersRepository usersRepository;

    private final BoardsRepository boardsRepository;
    private final BoardCommentRepository boardCommentRepository;
    private final Response response;

    public ResponseEntity<?> createBoard(BoardRequestDto.Create data) {

        Users user = usersRepository.getById(data.getUserId());
        Project project = projectRepository.getById(data.getProjectId());

        Boards boards = Boards.builder()
                .boardType(data.getType())
                .boardSubject(data.getSubject())
                .boardContent(data.getContent())
                .user(user)
                .project(project)
                .boardState("답변대기")
                .build();

        boardsRepository.save(boards);

        return response.success("글작성에 성공했습니다.");
    }

    public ResponseEntity<?> createComment(CommentRequestDto.Create data) {

        Boards board = boardsRepository.getById(data.getTargetId());
        Users user = usersRepository.getById(data.getUserId());

        BoardComment comment = BoardComment.builder()
                .comment(data.getComment())
                .boards(board)
                .user(user)
                .build();

        boardCommentRepository.save(comment);

        return response.success("문의 댓글 작성에 성공했습니다.");
    }

    public ResponseEntity<?> updateComment(CommentRequestDto.Update data) {

        Boards board = boardsRepository.getById(data.getTargetId());
        Users user = usersRepository.getById(data.getUserId());

        BoardComment comment = BoardComment.builder()
                .idx(data.getId())
                .comment(data.getComment())
                .boards(board)
                .user(user)
                .build();

        boardCommentRepository.save(comment);

        return response.success("문의 댓글 수정에 성공했습니다.");
    }

    public ResponseEntity<?> getCommentList(Long id) {

        List<BoardComment> comments = boardCommentRepository.findByBoardsIdx(id);
        List<CommentResponseDto.CommentListData> commentList = new ArrayList<>();

        for(BoardComment comment : comments) {
            CommentResponseDto.CommentListData dto = CommentResponseDto.CommentListData.builder()
                    .id(comment.getIdx())
                    .comment(comment.getComment())
                    .writer(comment.getUser().getName())
                    .createDate(comment.getCreateDate())
                    .build();

            commentList.add(dto);
        }

        return response.success(commentList);
    }

    public ResponseEntity<?> getBoardList(String boardType, String boardSubject, String boardContent, String boardState, int pageNum) {

        Page<Boards> boards = boardsRepository.findAllSearch(boardState, boardType, boardSubject, boardContent, PageRequest.of(pageNum, 10, Sort.by("create_date").descending()));
        return getResponseEntity(boards);
    }

    public ResponseEntity<?> getBoardListByProject(Long id, String boardState, String boardType, String boardSubject, String boardContent, int pageNum) {

        Page<Boards> boards = boardsRepository.findByProjectIdx(id, boardState, boardType, boardSubject, boardContent, PageRequest.of(pageNum, 10, Sort.by("create_date").descending()));
        return getResponseEntity(boards);
    }

    private ResponseEntity<?> getResponseEntity(Page<Boards> boards) {
        List<BoardResponseDto.BoardListData> boardList = new ArrayList<>();

        for(Boards board : boards) {
            BoardResponseDto.BoardListData dto = BoardResponseDto.BoardListData.builder()
                    .id(board.getIdx())
                    .type(board.getBoardType())
                    .subject(board.getBoardSubject())
                    .writer(board.getUser().getName())
                    .state(board.getBoardState())
                    .createDate(board.getCreateDate())
                    .build();

            boardList.add(dto);
        }

        BoardResponseDto.Paging page = BoardResponseDto.Paging.builder()
                .pageNumber(boards.getNumber())
                .pageSize(boards.getSize())
                .totalPages(boards.getTotalPages())
                .numberOfElements(boards.getNumberOfElements())
                .prev(boards.hasPrevious())
                .next(boards.hasNext())
                .last(boards.isLast())
                .first(boards.isFirst())
                .empty(boards.isEmpty())
                .build();

        BoardResponseDto.Board data = BoardResponseDto.Board.builder().boardData(boardList).paging(page).build();

        return response.success(data);
    }

    public ResponseEntity<?> getBoard(Long id) {
        Optional<Boards> item = boardsRepository.findById(id);

        if(item.isPresent()) {
            Boards board = item.get();

            BoardResponseDto.BoardData dto = BoardResponseDto.BoardData.builder()
                    .id(board.getIdx())
                    .projectId(board.getProject().getIdx())
                    .userId(board.getUser().getIdx())
                    .type(board.getBoardType())
                    .subject(board.getBoardSubject())
                    .content(board.getBoardContent())
                    .writer(board.getUser().getName())
                    .state(board.getBoardState())
                    .createDate(board.getCreateDate())
                    .build();

            return response.success(dto);
        }

        return null;
    }

    public ResponseEntity<?> updateBoard(BoardRequestDto.Update data) {

        Users user = usersRepository.getById(data.getUserId());
        Project project = projectRepository.getById(data.getProjectId());

        Boards boards = Boards.builder()
                .idx(data.getId())
                .user(user)
                .project(project)
                .boardType(data.getType())
                .boardSubject(data.getSubject())
                .boardContent(data.getContent())
                .boardState(data.getState())
                .build();

        boardsRepository.save(boards);

        return response.success("글수정에 성공하였습니다.");
    }

    public ResponseEntity<?>  deleteBoard(Long id) {
        boardsRepository.deleteById(id);

        return response.success("글삭제에 성공하였습니다.");
    }

}
