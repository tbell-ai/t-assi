package kr.co.tbell.tassi.repository;

import kr.co.tbell.tassi.entity.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByUserIdx(Long idx);

    @Query(
            value = "SELECT * FROM contact WHERE contact_service_name LIKE %:name% AND contact_service_type LIKE %:service% AND contact_test_type LIKE %:test% AND contact_state LIKE %:state%",
            countQuery = "SELECT COUNT(idx) FROM contact WHERE contact_service_name LIKE %:name% AND contact_service_type LIKE %:service% AND contact_test_type LIKE %:test% AND contact_state LIKE %:state%",
            nativeQuery = true
    )
    Page<Contact> findAllSearch(@Param("name") String serviceName, @Param("test") String testType, @Param("service") String serviceType, @Param("state") String state, Pageable pageable);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(
            value = "UPDATE contact SET contact_state=:state WHERE idx=:id",
            nativeQuery = true
    )
    int updateState(@Param("id") Long id, @Param("state") String state);

    @Query(
            value = "SELECT contact_state FROM contact WHERE idx=:id",
            nativeQuery = true
    )
    String findStateById(@Param("id") Long id);
}
