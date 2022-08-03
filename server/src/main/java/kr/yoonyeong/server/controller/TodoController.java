package kr.yoonyeong.server.controller;

import kr.yoonyeong.server.annotation.TodoRequestDefault;
import kr.yoonyeong.server.dto.TodoDTO;
import kr.yoonyeong.server.dto.TodoListResponseDTO;
import kr.yoonyeong.server.dto.TodoRequestDTO;
import kr.yoonyeong.server.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> retrieveTodoList(@TodoRequestDefault TodoRequestDTO todoRequestDTO){
        String userId = "temporary-user";

        log.info("TodoRequestDTO : {}",todoRequestDTO);
        List<TodoDTO> todoDTOList = todoService.retrieve(userId,todoRequestDTO);
        TodoListResponseDTO body = TodoListResponseDTO.builder()
            .todoDTOList(todoDTOList)
            .build();

        return ResponseEntity.ok(body);
    }

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO todoDTO){
        try{
            String userId = "temporary-user";

            List<TodoDTO> todoDTOList = todoService.create(todoDTO, userId);
            TodoListResponseDTO body = TodoListResponseDTO.builder()
                .todoDTOList(todoDTOList)
                .build();

            return ResponseEntity.status(HttpStatus.CREATED).body(body);
        } catch (Exception e) {

            String error= e.getMessage();

            TodoListResponseDTO responseBody = TodoListResponseDTO.builder()
                .error(error)
                .todoDTOList(Collections.emptyList())
                .build();

            return ResponseEntity.badRequest().body(responseBody);
        }
    }


    @PutMapping
    public ResponseEntity<?> updateTodo(@RequestBody TodoDTO todoDTO){
        String userId = "temporary-user";

        try{
            if(todoDTO.getId() == null) throw new Exception("Id Needed");

            List<TodoDTO> todoDTOList = todoService.update(todoDTO, userId);

            if(todoDTOList.size()==0) throw new Exception("No Such Id");

            TodoListResponseDTO body = TodoListResponseDTO.builder()
                .todoDTOList(todoDTOList)
                .build();

            return ResponseEntity.ok().body(body);

        } catch (Exception e) {

            TodoListResponseDTO body = TodoListResponseDTO.builder()
                .error(e.getMessage())
                .todoDTOList(Collections.emptyList())
                .build();
            return ResponseEntity.badRequest().body(body);
        }


    }


    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable String id){
        String userId = "temporary-user";

        TodoDTO dto = TodoDTO.builder()
            .id(id)
            .build();

        todoService.delete(dto, userId);
    }

}
