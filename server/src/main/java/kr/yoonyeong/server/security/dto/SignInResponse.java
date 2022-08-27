package kr.yoonyeong.server.security.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * @author rival
 * @since 2022-08-27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignInResponse {
    private int statusCode;
    private String message;
    private SignInResult content;
}
