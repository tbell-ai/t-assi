package kr.co.tbell.tassi.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "project")
public class Project extends BaseTime implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String projectLeader;

    @Column
    private String projectSummary;

    @Column
    private String projectPlan;

    @Column
    private String projectServiceType;

    @Column
    private String projectTestType;

    @Column
    private int percent;                        // 프로젝트 진행률

    @Column
    private int totalDefect;                    // 프로젝트 발생결함

    @Column
    private int remainDefect;                   // 프로젝트 남은 결함

    @Column
    private int solveDefect;                    // 프로젝트 해결 결함

    @Column
    private int neglectDefect;                  // 프로젝트 방치 결함

    @Column
    private String projectBtsLink;                  // 지라 링크

    @Column
    private String projectBtsAccount;                  // 지라 링크

    @Column
    private String projectBtsPassword;                  // 지라 링크

    @Column
    private String projectState;                    // 프로젝트 상태

    @OneToOne(targetEntity=Contract.class, fetch=FetchType.LAZY)
    @JoinColumn(name="contract_idx", referencedColumnName="idx", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Contract contract;

    @ManyToOne(targetEntity=Users.class, fetch=FetchType.LAZY)
    @JoinColumn(name="user_idx", referencedColumnName="idx", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Users user;
}
