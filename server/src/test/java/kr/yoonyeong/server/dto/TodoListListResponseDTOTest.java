package kr.yoonyeong.server.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author rival
 * @since 2022-08-03
 */
class TodoListListResponseDTOTest {


    @DisplayName("1. TodoListResponseDTO method test")
    @Test
    void test_1() {

        List<TodoDTO> todoDTOList = List.of(TodoDTO.builder().done(false).title("Todo1").id("user1").build(),TodoDTO.builder().done(false).title("Todo2").id("user2").build());
        TodoListListResponseDTO.builder().todoDTOList(todoDTOList);
    }

}