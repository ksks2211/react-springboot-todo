package kr.yoonyeong.server.dto;

import kr.yoonyeong.server.entity.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import java.time.LocalDateTime;

/**
 * @author rival
 * @since 2022-07-19
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoDTO {

    private String id;
    private String title;
    private boolean done;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    // mapping entity -> dto
    public TodoDTO(final TodoEntity entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.done = entity.isDone();
        this.createdAt = entity.getCreatedAt();
        this.modifiedAt = entity.getModifiedAt();
    }


    // mapping dto -> entity
    public static TodoEntity toEntity(final TodoDTO dto,final String userId){
        return TodoEntity.builder()
            .id(dto.getId())
            .userId(userId)
            .title(dto.getTitle())
            .done(dto.isDone())
            .build();
    }




}
