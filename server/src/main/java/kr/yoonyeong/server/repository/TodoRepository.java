package kr.yoonyeong.server.repository;

import kr.yoonyeong.server.entity.TodoEntity;
import kr.yoonyeong.server.security.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author rival
 * @since 2022-07-20
 */
@Repository
public interface TodoRepository extends JpaRepository<TodoEntity,String> {

    Page<TodoEntity> findByMember(Member member, Pageable pageable);

//    @Query("select t from TodoEntity t where t.member = :member")
//    Page<TodoEntity> findByMemberJPQL(Member member,Pageable pageable);


    Long countByMember(Member member);
}
