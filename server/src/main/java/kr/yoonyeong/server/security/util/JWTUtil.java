package kr.yoonyeong.server.security.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import kr.yoonyeong.server.security.dto.JWTVerifyResult;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author rival
 * @since 2022-08-27
 */
@Component
public class JWTUtil {

    private static final long AUTH_EXP_MINUTES=30;
    private static final String SECRET_KEY="SECRET_KEY";

    private static Date getExp(){
        return Date.from(Instant.now().plus(AUTH_EXP_MINUTES,ChronoUnit.MINUTES));
    }
    private static Algorithm getAlgo(){
        return Algorithm.HMAC256(SECRET_KEY);
    }

    // token 생성
    public static String makeAuthToken(User user){
        return JWT.create()
            .withSubject(user.getUsername())
            .withExpiresAt(getExp())
            .withIssuer("Dodo App")
            .withIssuedAt(new Date())
            .sign(getAlgo());
    }

    // token 검증
    public static JWTVerifyResult verifyToken(String token){
        try{
            DecodedJWT result = JWT.require(getAlgo())
                .build()
                .verify(token);
            return new JWTVerifyResult(true, result.getSubject());
        }catch(JWTVerificationException e){
            try {
                DecodedJWT result = JWT.decode(token);
                return new JWTVerifyResult(false, result.getSubject());
            }catch(JWTDecodeException exception){
                return new JWTVerifyResult(false,null);
            }
        }
    }



}
