package kr.yoonyeong.server.controller;

import kr.yoonyeong.server.annotation.TodoListRequestDefault;
import kr.yoonyeong.server.dto.TodoDTO;
import kr.yoonyeong.server.dto.TodoListListResponseDTO;
import kr.yoonyeong.server.dto.TodoListRequestDTO;
import kr.yoonyeong.server.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * @author rival
 * @since 2022-07-20
 */
@RestController
@RequestMapping("todo")
@RequiredArgsConstructor
@Slf4j
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<?> retrieveTodoList(@AuthenticationPrincipal User user, @TodoListRequestDefault TodoListRequestDTO todoListRequestDTO){

        log.info("TodoRequestDTO : {}", todoListRequestDTO);
        List<TodoDTO> todoDTOList = todoService.retrieve(user.getUsername(), todoListRequestDTO);
        TodoListListResponseDTO body = TodoListListResponseDTO.builder()
            .todoDTOList(todoDTOList)
            .build();

        return ResponseEntity.ok(body);
    }

    @PostMapping
    public ResponseEntity<?> createTodo(@AuthenticationPrincipal User user, @RequestBody TodoDTO todoDTO){

        try{
            List<TodoDTO> todoDTOList = todoService.create(todoDTO, user.getUsername());
            TodoListListResponseDTO body = TodoListListResponseDTO.builder()
                .todoDTOList(todoDTOList)
                .build();
            return ResponseEntity.status(HttpStatus.CREATED).body(body);
        } catch (Exception e) {
            String error= e.getMessage();
            TodoListListResponseDTO responseBody = TodoListListResponseDTO.builder()
                .error(error)
                .todoDTOList(Collections.emptyList())
                .build();
            return ResponseEntity.badRequest().body(responseBody);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateTodo(@AuthenticationPrincipal User user,@RequestBody TodoDTO todoDTO){
        try{
            if(todoDTO.getId() == null) throw new Exception("Id Needed");
            todoService.update(todoDTO, user.getUsername());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@AuthenticationPrincipal User user,@PathVariable String id){
        TodoDTO dto = TodoDTO.builder()
            .id(id)
            .build();
        todoService.delete(dto, user.getUsername());
    }

}
