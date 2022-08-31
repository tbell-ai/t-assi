package kr.co.tbell.tassi.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "contact_comment")
public class ContactComment extends BaseTime implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "contact_comment")
    private String comment;

    @ManyToOne(targetEntity=Contact.class, fetch=FetchType.LAZY)
    @JoinColumn(name="contact_idx", referencedColumnName="idx", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Contact contact;

    @ManyToOne(targetEntity=Users.class, fetch=FetchType.LAZY)
    @JoinColumn(name="user_idx", referencedColumnName="idx", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Users user;

}
