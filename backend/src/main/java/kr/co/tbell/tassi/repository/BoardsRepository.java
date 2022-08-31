package kr.co.tbell.tassi.repository;

import kr.co.tbell.tassi.entity.Boards;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BoardsRepository extends JpaRepository<Boards, Long> {
    @Query(
            value = "SELECT * FROM board WHERE board_type LIKE %:boardType% AND board_subject LIKE %:boardSubject% AND board_content LIKE %:boardContent% AND board_state LIKE %:boardState%",
            countQuery = "SELECT COUNT(idx) FROM board WHERE board_type LIKE %:boardType% AND board_subject LIKE %:boardSubject% AND board_content LIKE %:boardContent% AND board_state LIKE %:boardState%",
            nativeQuery = true
    )
    Page<Boards> findAllSearch(@Param("boardState") String boardState, @Param("boardType") String boardType, @Param("boardSubject") String boardSubject, @Param("boardContent") String boardContent, Pageable pageable);

    @Query(
            value = "SELECT * FROM board b WHERE b.project_id=:idx AND board_type LIKE %:boardType% AND board_subject LIKE %:boardSubject% AND board_content LIKE %:boardContent% AND board_state LIKE %:boardState%",
            countQuery = "SELECT COUNT(b.idx) FROM board b WHERE b.project_id=:idx AND board_type LIKE %:boardType% AND board_subject LIKE %:boardSubject% AND board_content LIKE %:boardContent% AND board_state LIKE %:boardState%",
            nativeQuery = true
    )
    Page<Boards> findByProjectIdx(@Param("idx") Long idx, @Param("boardState") String boardState, @Param("boardType") String boardType, @Param("boardSubject") String boardSubject, @Param("boardContent") String boardContent, Pageable pageable);
}
