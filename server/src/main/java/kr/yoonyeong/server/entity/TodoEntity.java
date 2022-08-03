package kr.yoonyeong.server.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

/**
 * @author rival
 * @since 2022-07-19
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@ToString(callSuper = true)
public class TodoEntity  extends BaseEntity{

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "uuid")
    private String id;

    private String userId;
    private String title;
    private boolean done;


    public void toggleState(){
        done=!done;
    }
    public void changeState(boolean done){
        this.done=done;
    }
    public void changeTitle(String title){
        this.title=title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TodoEntity that = (TodoEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
