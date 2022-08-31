package kr.co.tbell.tassi.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "company")
public class Company extends BaseTime implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String companyName;             // 업체명

    @Column
    private String companyAddress;          // 업체주소

    @Column
    private String companyCeoName;          // 대표자명

    @Column
    private String companyManagerName;      // 담당자명

    @Column
    private String companyPhone;            // 업체 대표번호

    @Column
    private String companyEmail;            // 업체 이메일

    @Column
    private String companyManagerPhone;     // 담당자 번호

    @Column
    private String companyManagerEmail;     // 담당자 이메일

    @Column
    private String companyState;            // 업체 상태

    @ManyToOne(targetEntity=Users.class, fetch=FetchType.LAZY)
    @JoinColumn(name="user_idx", referencedColumnName="idx", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Users user;

}
