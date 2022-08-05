package kr.yoonyeong.server.security.dto;

import kr.yoonyeong.server.dto.ResponseDTO;
import lombok.Builder;

import java.util.List;


/**
 * @author rival
 * @since 2022-08-03
 */
public class UserResponseDTO extends ResponseDTO<UserDTO> {
    public UserDTO getUser(){
        return this.data != null && this.data.size() > 0 ? this.data.get(0) : null;
    }

    @Builder
    public UserResponseDTO(UserDTO userDTO, String error){
        super(userDTO == null ? null : List.of(userDTO),error);
    }
}
