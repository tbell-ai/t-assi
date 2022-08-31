package kr.co.tbell.tassi.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "contract")
public class Contract extends BaseTime implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String contractName;                // 계약명

    @Column
    private int contractCharge;                 // 계약금

    @Column
    private String contractDay;                 // 계약일

    @Column
    private String contractStart;               // 계약기간 - 시작일

    @Column
    private String contractEnd;                 // 계약기간 - 종료일

    @Column
    private String contractContent;             // 계약내용

    @Column
    private int contractLowLevel;               // 투입인원 - 초급

    @Column
    private int contractMiddleLevel;            // 투입인원 - 중급

    @Column
    private int contractHighLevel;              // 투입인원 - 고급

    @Column
    private int contractSpecialLevel;           // 투입인원 - 특급

    @Column
    private String contractResultDocument;      // 최종산출물

    @Column
    private String contractResultDelivery;           // 계약금납입일

    @Column
    private String contractState;           // 계약 상태

    @ManyToOne(targetEntity=Company.class, fetch=FetchType.LAZY)
    @JoinColumn(name="company_idx", referencedColumnName="idx", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Company company;

    @ManyToOne(targetEntity=Users.class, fetch=FetchType.LAZY)
    @JoinColumn(name="user_idx", referencedColumnName="idx", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Users user;

}
