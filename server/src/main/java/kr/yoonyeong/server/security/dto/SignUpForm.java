package kr.yoonyeong.server.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rival
 * @since 2022-08-27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpForm {
    private String email;
    private String password;
    private String username;
}
