package kr.co.tbell.tassi.repository;

import kr.co.tbell.tassi.entity.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    @Query(
            value = "SELECT * FROM contract WHERE contract_name LIKE %:contractName% AND contract_state LIKE %:contractState%",
            countQuery = "SELECT COUNT(idx) FROM contract WHERE contract_name LIKE %:contractName% AND contract_state LIKE %:contractState%",
            nativeQuery = true
    )
    Page<Contract> findAllSearch(@Param("contractName") String contractName, @Param("contractState") String contractState, Pageable pageable);
}
