package kr.co.tbell.tassi.repository;

import kr.co.tbell.tassi.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
    boolean existsByEmail(String email);

    @Query(
            value = "SELECT * FROM user WHERE name LIKE %:name% AND email LIKE %:email% AND state LIKE %:state%",
            countQuery = "SELECT COUNT(idx) FROM user WHERE name LIKE %:name% AND email LIKE %:email% AND state LIKE %:state%",
            nativeQuery = true
    )
    Page<Users> findAllSearch(@Param("name") String name, @Param("email") String email, @Param("state") String state, Pageable pageable);

}