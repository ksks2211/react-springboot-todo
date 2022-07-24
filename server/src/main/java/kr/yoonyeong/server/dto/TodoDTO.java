package kr.yoonyeong.server.dto;

import kr.yoonyeong.server.model.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    // mapping entity -> dto
    public TodoDTO(final TodoEntity entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.done = entity.isDone();
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
