package kr.yoonyeong.server.security.service;

import kr.yoonyeong.server.security.dto.UserDTO;
import kr.yoonyeong.server.security.entity.UserEntity;
import kr.yoonyeong.server.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author rival
 * @since 2022-08-03
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDTO create(final UserDTO userDTO) {



        if(userDTO == null || userDTO.getEmail() == null){
            throw new RuntimeException("Invalid argument(s)");
        }

        UserEntity userEntity = UserDTO.toEntity(userDTO);
        final String email = userEntity.getEmail().trim();

        if(userRepository.existsByEmail(email)){
            log.warn("Email already exists {}",email);
            throw new RuntimeException("Email already exists");
        }

        return UserDTO.toDTO(userRepository.save(userEntity));
    }

    @Override
    public UserDTO getByCredentials(final String email, final String password) {
        UserEntity userEntity = userRepository.findByEmailAndPassword(email, password);
        return UserDTO.toDTO(userEntity);
    }
}
