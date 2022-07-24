package kr.yoonyeong.server.service;

import kr.yoonyeong.server.dto.TodoDTO;
import kr.yoonyeong.server.model.TodoEntity;
import kr.yoonyeong.server.persistence.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author rival
 * @since 2022-07-20
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TodoServiceImpl implements TodoService {


    private final TodoRepository todoRepository;

    @Override
    public List<TodoDTO> create(final TodoDTO todoDTO, String userId) {

        TodoEntity entity = TodoDTO.toEntity(todoDTO, userId);

        validateEntity(entity);

        TodoDTO dto = new TodoDTO(todoRepository.save(entity));

        return List.of(dto);
    }

    @Override
    public List<TodoDTO> retrieve(String userId) {
        return todoRepository.findByUserId(userId).stream().map(TodoDTO::new).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public List<TodoDTO> update(final TodoDTO todoDTO, String userId) {

        TodoEntity entity = TodoDTO.toEntity(todoDTO, userId);
        validateEntity(entity);
        Optional<TodoEntity> original = todoRepository.findById(entity.getId());

        if(original.isPresent()){
            TodoEntity changed = original.get();
            changed.changeState(todoDTO.isDone());
            changed.changeTitle(todoDTO.getTitle());
            todoRepository.save(changed);
            return List.of(new TodoDTO(changed));
        }

        return Collections.emptyList();
    }

    @Override
    public void delete(final TodoDTO todoDTO,String userId) {

        validateEntity(TodoDTO.toEntity(todoDTO,userId));

        if(todoDTO.getId() == null) return;
        todoRepository.deleteById(todoDTO.getId());

    }


    private void validateEntity(final TodoEntity todoEntity){
        if(todoEntity == null){
            log.warn("Entity Cannot be null.");
            throw new RuntimeException("Entity cannot be null.");
        }

        if(todoEntity.getUserId() == null){
            log.warn("Unknown user.");
            throw new RuntimeException("Unknown user.");
        }
    }


}
