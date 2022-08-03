package kr.yoonyeong.server.repository;

import kr.yoonyeong.server.entity.TodoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * @author rival
 * @since 2022-07-20
 */
@Repository
public interface TodoRepository extends JpaRepository<TodoEntity,String> {

    Page<TodoEntity> findByUserId(String userId, Pageable pageable);



    @Query("select t from TodoEntity t where t.userId = :userId")
    List<TodoEntity> findByUserIdJPQL(String userId);


}
