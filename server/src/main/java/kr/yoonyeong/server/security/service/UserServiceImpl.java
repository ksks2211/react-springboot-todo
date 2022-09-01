package kr.yoonyeong.server.security.service;

import kr.yoonyeong.server.exception.NotMatchingPasswordException;
import kr.yoonyeong.server.security.dto.SignUpForm;
import kr.yoonyeong.server.security.entity.Member;
import kr.yoonyeong.server.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author rival
 * @since 2022-08-03
 */
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User create(SignUpForm signUpForm) {
        String email = signUpForm.getEmail();
        String password = passwordEncoder.encode(signUpForm.getPassword());
        String username = signUpForm.getUsername();

        if(!StringUtils.hasText(email) || !StringUtils.hasText(password)){
            throw new RuntimeException("Invalid argument(s)");
        }

        if(userRepository.existsByEmail(email)){
            log.warn("Email already exists {}",email);
            throw new RuntimeException("Email already exists");
        }

        Member member = Member.builder()
            .email(email)
            .password(password)
            .username(username)
            .authorities("ROLE_USER")
            .build();

        return toUser(userRepository.save(member));
    }

    @Override
    public User getUserByCredentials(final String email, final String password) throws AuthenticationException {

        Member member = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        if(passwordEncoder.matches(password, member.getPassword()))
            return toUser(member);
        else throw new NotMatchingPasswordException("Password Not Matching");
    }

    @Override
    public User getUserByEmail(String email) throws UsernameNotFoundException {
        Member member = userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException(email));
        return toUser(member);
    }

    @Override
    public User getUserById(String id) throws UsernameNotFoundException {
        return toUser(userRepository.findById(id).orElseThrow(()->new UsernameNotFoundException(id)));
    }


    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return getUserByEmail(email);
    }

    public static User toUser(Member member){
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(member.getAuthorities());
        return new User(member.getId(),member.getPassword(), authorities);
    }


}
