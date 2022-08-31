package kr.co.tbell.tassi.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "contact")
public class Contact extends BaseTime implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "contact_service_name")
    private String contactServiceName;

    @Column(name = "contact_service_type")
    private String contactServiceType;

    @Column(name = "contact_test_type")
    private String contactTestType;

    @Column(name = "contact_start")
    private String contactStart;

    @Column(name = "contact_end")
    private String contactEnd;

    @Column(name = "contact_content")
    private String contactContent;

    @Column(name = "contact_state")
    private String contactState;

    @ManyToOne(targetEntity=Users.class, fetch=FetchType.LAZY)
    @JoinColumn(name="user_idx", referencedColumnName="idx", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Users user;

}
