package kr.co.tbell.tassi.repository;

import kr.co.tbell.tassi.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query(
            value = "SELECT * FROM project WHERE project_state LIKE %:projectState%",
            countQuery = "SELECT COUNT(idx) FROM project WHERE project_state LIKE %:projectState%",
            nativeQuery = true
    )
    Page<Project> findAllSearch(@Param("projectState") String projectState, Pageable pageable);

    @Query(
            value = "SELECT * FROM project p WHERE p.user_idx=:idx",
            countQuery = "SELECT COUNT(p.idx) FROM project p WHERE p.user_idx=:idx",
            nativeQuery = true
    )
    List<Project> findByUserIdx(@Param("idx") Long idx);
}
