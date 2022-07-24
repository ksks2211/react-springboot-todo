package kr.yoonyeong.server.persistence;

import kr.yoonyeong.server.model.TodoEntity;
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

    List<TodoEntity> findByUserId(String userId);



    @Query("select t from TodoEntity t where t.userId = :userId")
    List<TodoEntity> findByUserIdJPQL(String userId);


}
