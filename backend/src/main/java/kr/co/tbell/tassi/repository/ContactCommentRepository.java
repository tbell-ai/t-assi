package kr.co.tbell.tassi.repository;

import kr.co.tbell.tassi.entity.ContactComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactCommentRepository extends JpaRepository<ContactComment, Long> {
    List<ContactComment> findByContactIdx(Long idx);
}
