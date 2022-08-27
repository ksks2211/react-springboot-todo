package kr.yoonyeong.server.service;

import kr.yoonyeong.server.dto.TodoDTO;
import kr.yoonyeong.server.dto.TodoListRequestDTO;
import kr.yoonyeong.server.entity.TodoEntity;
import kr.yoonyeong.server.repository.TodoRepository;
import kr.yoonyeong.server.security.entity.Member;
import kr.yoonyeong.server.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
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
    private final UserRepository userRepository;

    @Override
    @Transactional
    public List<TodoDTO> create(final TodoDTO todoDTO, String userId) throws UsernameNotFoundException{
        Member member = userRepository
            .findById(userId)
            .orElseThrow(
                ()->new UsernameNotFoundException(userId)
            );
        TodoEntity entity = TodoDTO.toEntity(todoDTO, member);
        validateEntity(entity);
        TodoDTO dto = new TodoDTO(todoRepository.save(entity));
        return List.of(dto);
    }

    @Override
    @Transactional
    public List<TodoDTO> retrieve(final String userId, TodoListRequestDTO todoListRequestDTO)throws UsernameNotFoundException {
        Pageable pageable = todoListRequestDTO.getPageable();
        Member member = userRepository
            .findById(userId)
            .orElseThrow(
                ()->new UsernameNotFoundException(userId)
            );
        return todoRepository.findByMember(member,pageable).stream().map(TodoDTO::new).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public void update(final TodoDTO todoDTO, String userId) throws UsernameNotFoundException,EntityNotFoundException {
        Member member = userRepository
            .findById(userId)
            .orElseThrow(
                ()->new UsernameNotFoundException(userId)
            );
        TodoEntity entity = TodoDTO.toEntity(todoDTO, member);

        validateEntity(entity);

        TodoEntity changed = todoRepository.findById(entity.getId()).orElseThrow(()->new EntityNotFoundException(todoDTO.getId()));
        changed.changeState(todoDTO.isDone());
        changed.changeTitle(todoDTO.getTitle());
        todoRepository.save(changed);
    }

    @Override
    @Transactional
    public void delete(final TodoDTO todoDTO,String userId) throws UsernameNotFoundException {

        Member member = userRepository
            .findById(userId)
            .orElseThrow(
                ()->new UsernameNotFoundException(userId)
            );
        validateEntity(TodoDTO.toEntity(todoDTO,member));

        if(todoDTO.getId() == null) return;
        todoRepository.deleteById(todoDTO.getId());
    }


    private void validateEntity(final TodoEntity todoEntity){
        if(todoEntity == null){
            log.warn("Entity Cannot be null.");
            throw new RuntimeException("Entity cannot be null.");
        }

        if(todoEntity.getMember() == null){
            log.warn("Unknown user.");
            throw new RuntimeException("Unknown user.");
        }
    }


}
