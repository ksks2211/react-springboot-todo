package kr.yoonyeong.server.config;

import kr.yoonyeong.server.entity.TodoEntity;
import kr.yoonyeong.server.repository.TodoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author rival
 * @since 2022-08-03
 */

@Configuration
public class TestConfig {
    @Bean
    public CommandLineRunner runner(TodoRepository todoRepository){
        return args ->{
            List<TodoEntity> todos = IntStream.rangeClosed(0, 30)
                .mapToObj(i -> TodoEntity.builder().title("Todo" + i).done(false).userId("temporary-user").build()).collect(Collectors.toList());
            todoRepository.saveAll(todos);
        };

    }
}
