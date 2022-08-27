package kr.yoonyeong.server.security.dto;

import lombok.Getter;

/**
 * @author rival
 * @since 2022-08-27
 */
@Getter
public class JWTVerifyResult {
    private final boolean success;
    private final String userId;
    public JWTVerifyResult(boolean success, String userId){
        this.success=success;
        this.userId=userId;
    }
}
