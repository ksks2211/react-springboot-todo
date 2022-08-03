package kr.yoonyeong.server.security.entity;

import kr.yoonyeong.server.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

/**
 * @author rival
 * @since 2022-07-24
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable=false)
    private String password;

}
