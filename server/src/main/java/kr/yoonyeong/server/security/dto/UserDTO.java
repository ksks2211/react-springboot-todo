package kr.yoonyeong.server.security.dto;

import kr.yoonyeong.server.security.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rival
 * @since 2022-08-03
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String token;
    private String email;
    private String username;
    private String password;
    private Long id;


    public static UserEntity toEntity(UserDTO dto){
        return UserEntity.builder()
            .username(dto.getUsername())
            .password(dto.getPassword())
            .email(dto.getEmail())
            .build();
    }

    public static UserDTO toDTO(UserEntity entity){

        if (entity==null) return null;
        return UserDTO.builder()
            .username(entity.getUsername())
            // .password(entity.getPassword())
            .email(entity.getPassword())
            .id(entity.getId())
            .build();
    }
}
