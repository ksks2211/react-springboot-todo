package kr.yoonyeong.server.config;

import kr.yoonyeong.server.entity.TodoEntity;
import kr.yoonyeong.server.repository.TodoRepository;
import kr.yoonyeong.server.security.entity.Member;
import kr.yoonyeong.server.security.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author rival
 * @since 2022-08-03
 */

@Configuration
public class TestConfig {
    @Bean
    public CommandLineRunner runner(TodoRepository todoRepository, UserRepository userRepository){
        return args ->{

            Member member = Member.builder().email("example@google.com")
                .username("user")
                .password("12345")
                .build();
            userRepository.save(member);

            List<TodoEntity> todos = IntStream.rangeClosed(0, 30)
                .mapToObj(i -> TodoEntity.builder().title("Todo" + i).done(false).member(member).build()).collect(Collectors.toList());
            todoRepository.saveAll(todos);
        };

    }
}
