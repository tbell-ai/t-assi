package kr.co.tbell.tassi.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "board_comment")
public class BoardComment extends BaseTime implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "board_comment")
    private String comment;

    @ManyToOne(targetEntity=Boards.class, fetch=FetchType.LAZY)
    @JoinColumn(name="board_idx", referencedColumnName="idx", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Boards boards;

    @ManyToOne(targetEntity=Users.class, fetch=FetchType.LAZY)
    @JoinColumn(name="user_idx", referencedColumnName="idx", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Users user;
}
