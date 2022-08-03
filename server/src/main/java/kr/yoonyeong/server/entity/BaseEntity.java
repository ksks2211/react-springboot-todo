package kr.yoonyeong.server.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * @author rival
 * @since 2022-07-24
 */

@MappedSuperclass
@EntityListeners(value={AuditingEntityListener.class})
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public abstract class BaseEntity {

    @CreatedDate
    @Column(name="created_at",updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name="modified_at")
    private LocalDateTime modifiedAt;
}
