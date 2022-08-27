package kr.yoonyeong.server.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author rival
 * @since 2022-08-27
 */
public class NotMatchingPasswordException extends AuthenticationException {

    public NotMatchingPasswordException(String msg){
        super(msg);
    }
    public NotMatchingPasswordException(String msg, Throwable cause){
        super(msg,cause);
    }
}

