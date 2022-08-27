package kr.yoonyeong.server.security.repository;

import kr.yoonyeong.server.security.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author rival
 * @since 2022-07-24
 */
@Repository
public interface UserRepository extends JpaRepository<Member,String> {

    Optional<Member> findByEmail(String email);

    Boolean existsByEmail(String email);

    Optional<Member> findByEmailAndPassword(String email, String password);

}
