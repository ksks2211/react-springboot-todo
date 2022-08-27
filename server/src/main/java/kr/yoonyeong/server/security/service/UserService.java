package kr.yoonyeong.server.security.service;

import kr.yoonyeong.server.security.dto.SignUpForm;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author rival
 * @since 2022-08-03
 */
public interface UserService extends UserDetailsService {
    User create(final SignUpForm signUpForm);
    User getUserByCredentials(final String email, final String password) throws AuthenticationException;
    User getUserByEmail(final String email);
    User getUserById(String id);
}
