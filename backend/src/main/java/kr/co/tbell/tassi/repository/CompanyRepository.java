package kr.co.tbell.tassi.repository;

import kr.co.tbell.tassi.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query(
            value = "SELECT * FROM company WHERE company_name LIKE %:companyName% AND company_ceo_name LIKE %:companyCeoName% AND company_manager_name LIKE %:companyManagerName%",
            countQuery = "SELECT COUNT(idx) FROM company WHERE company_name LIKE %:companyName% AND company_ceo_name LIKE %:companyCeoName% AND company_manager_name LIKE %:companyManagerName%",
            nativeQuery = true
    )
    Page<Company> findAllSearch(@Param("companyName") String companyName, @Param("companyCeoName") String companyCeoName, @Param("companyManagerName") String companyManagerName, Pageable pageable);
}
