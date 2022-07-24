package kr.yoonyeong.server.service;

import kr.yoonyeong.server.dto.TodoDTO;

import java.util.List;

/**
 * @author rival
 * @since 2022-07-20
 */
public interface TodoService {

    public List<TodoDTO> create(final TodoDTO todoDTO, String userId);

    public List<TodoDTO> retrieve(final String userId);

    public List<TodoDTO> update(final TodoDTO todoDTO, String userId);

    public void delete(final TodoDTO todoDTO, String userId);
}
