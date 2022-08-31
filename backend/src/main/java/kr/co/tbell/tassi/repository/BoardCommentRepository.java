package kr.co.tbell.tassi.repository;

import kr.co.tbell.tassi.entity.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {
    List<BoardComment> findByBoardsIdx(Long idx);
}
