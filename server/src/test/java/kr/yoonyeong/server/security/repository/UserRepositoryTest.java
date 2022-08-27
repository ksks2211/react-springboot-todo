package kr.yoonyeong.server.security.repository;

import kr.yoonyeong.server.security.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author rival
 * @since 2022-07-24
 */
@DataJpaTest
class UserRepositoryTest {
    
    @Autowired
    private UserRepository userRepository;
    
    
    @DisplayName("1. Creating UserEntity Test")
    @Test
    public void create_user_entity(){
        String email = "email@example.com";
        String username = "username";
        String password="password";

        Member entity = create_entity(email, username, password);
        
        userRepository.save(entity);

        Member created = userRepository.findById(entity.getId()).get();

        assertThat(created).isNotNull();
        assertThat(created.getUsername()).isEqualTo(username);
        assertThat(created.getEmail()).isEqualTo(email);
        assertThat(created.getPassword()).isEqualTo(password);

    }

    @DisplayName("2. Finding UserEntity By Email Test")
    @Test
    public void find_by_email(){
        String email = "email2@example.com";
        String username = "username";
        String password="password";

        userRepository.save(create_entity(email,username,password));

        Member result = userRepository.findByEmail(email);

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo(username);
        assertThat(result.getPassword()).isEqualTo(password);
    }
    
    private Member create_entity(String email, String username, String password){
        return Member.builder()
            .email(email)
            .username(username)
            .password(password)
            .build();
    }

}