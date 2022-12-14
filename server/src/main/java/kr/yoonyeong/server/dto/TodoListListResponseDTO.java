package kr.yoonyeong.server.dto;

import lombok.Builder;

import java.util.List;

/**
 * @author rival
 * @since 2022-07-19
 */


public class TodoListListResponseDTO extends ListResponseDTO<TodoDTO> {
    public List<TodoDTO> getTodoList(){
        return this.data;
    }


    @Builder
    public TodoListListResponseDTO(List<TodoDTO> todoDTOList, String error){
        super(todoDTOList,error);
    }
}
