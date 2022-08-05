package kr.yoonyeong.server.security.service;

import kr.yoonyeong.server.security.dto.UserDTO;
import kr.yoonyeong.server.security.entity.UserEntity;

/**
 * @author rival
 * @since 2022-08-03
 */
public interface UserService {
    UserDTO create(final UserDTO userDTO);
    UserDTO getByCredentials(final String email, final String password);
}
