package kr.yoonyeong.server.persistence;

import kr.yoonyeong.server.model.TodoEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author rival
 * @since 2022-07-20
 */

@DataJpaTest
class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;


    @DisplayName("1. Saving todoEntity test")
    @Test
    public void todoRepository_save_test(){

        String userId = "user1";
        String title = "Wash Dishes";
        boolean done = false;

        TodoEntity todo = create_entity(userId, title, done);
        todoRepository.save(todo);

        TodoEntity created = todoRepository.findById(todo.getId()).get();

        assertThat(created).isNotNull();
        assertThat(created.getUserId()).isEqualTo(userId);
        assertThat(created.getTitle()).isEqualTo(title);
        assertThat(created.isDone()).isEqualTo(done);
    }

    @DisplayName("2. Finding todoEntity by userId test")
    @Test
    public void todoRepository_findByUserId_test(){
        String userId = "user2";
        String title = "Eat Salad";
        boolean done = false;

        TodoEntity todo = create_entity(userId, title, done);
        todoRepository.save(todo);

        List<TodoEntity> todoEntityList = todoRepository.findByUserId(todo.getUserId());

        assertThat(todoEntityList).hasSizeGreaterThan(0);
        todoEntityList.forEach(el->{
            assertThat(el.getUserId()).isEqualTo(userId);
        });

    }




    TodoEntity create_entity(String userId, String title, boolean done){
        return TodoEntity.builder()
            .userId(userId)
            .title(title)
            .done(done)
            .build();
    }
}