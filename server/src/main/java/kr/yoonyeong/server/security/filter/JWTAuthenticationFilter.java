package kr.yoonyeong.server.security.filter;

import com.google.common.net.HttpHeaders;
import kr.yoonyeong.server.security.dto.JWTVerifyResult;
import kr.yoonyeong.server.security.service.UserService;
import kr.yoonyeong.server.security.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author rival
 * @since 2022-08-27
 */
@Slf4j
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    private final UserService userService;
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, UserService userService) {
        super(authenticationManager);
        this.userService=userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")){
            String token = authHeader.substring(7);
            JWTVerifyResult verifyResult = JWTUtil.verifyToken(token);

            if(verifyResult.isSuccess()){
                User user = userService.getUserById(verifyResult.getUserId());
                user.eraseCredentials(); // hide password


                // create auth token
                // 직접 Token 생성
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    user.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Save Token
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);
            }else{
                logger.error("Failed to create authentication in security context");
            }
        }

        chain.doFilter(request,response);
    }
}
