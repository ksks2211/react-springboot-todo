package kr.yoonyeong.server.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author rival
 * @since 2022-07-19
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class TodoEntity {

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
}
