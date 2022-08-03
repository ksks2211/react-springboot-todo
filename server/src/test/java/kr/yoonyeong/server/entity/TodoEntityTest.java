package kr.yoonyeong.server.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author rival
 * @since 2022-07-19
 */

class TodoEntityTest {


    @DisplayName("Builder Test")
    @Test
    public void builder_test(){

        String userId = "userId";
        boolean done = false;
        String id = "id1234";

        TodoEntity todoEntity = TodoEntity.builder()
            .id(id)
            .done(done)
            .userId(userId)
            .build();


        assertThat(todoEntity.getId()).isEqualTo(id);
        assertThat(todoEntity.getUserId()).isEqualTo(userId);
        assertThat(todoEntity.isDone()).isFalse();


    }

}