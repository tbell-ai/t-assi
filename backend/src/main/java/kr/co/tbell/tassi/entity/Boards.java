package kr.co.tbell.tassi.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "board")
public class Boards extends BaseTime implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "board_type")
    private String boardType;    // 문의 유형

    @Column(name = "board_subject")
    private String boardSubject; // 문의 제목

    @Column(name = "board_content")
    private String boardContent; // 문의 내용
    
    @Column(name = "board_status")
    private String boardState;  // 문의 상태

    @ManyToOne(targetEntity=Project.class, fetch=FetchType.LAZY)
    @JoinColumn(name="project_id", referencedColumnName="idx", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Project project;

    @ManyToOne(targetEntity=Users.class, fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", referencedColumnName="idx", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Users user;
}
