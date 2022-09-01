package kr.yoonyeong.server.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.yoonyeong.server.security.dto.SignInForm;
import kr.yoonyeong.server.security.dto.SignInResponse;
import kr.yoonyeong.server.security.dto.SignInResult;
import kr.yoonyeong.server.security.util.JWTUtil;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author rival
 * @since 2022-08-27
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    public JWTLoginFilter(String filterProcessesUrl, AuthenticationManager authenticationManager){
        super(filterProcessesUrl, authenticationManager);
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {

        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        SignInForm signInForm = objectMapper.readValue(request.getReader(),SignInForm.class);

        String email = signInForm.getEmail();
        String password = signInForm.getPassword();

        if(!StringUtils.hasText(email) || !StringUtils.hasText(password)){
            throw new UsernameNotFoundException(email);
        }

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);
        return getAuthenticationManager().authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {

        User user= (User)authResult.getPrincipal();
        String token = JWTUtil.makeAuthToken(user);

        SignInResult signInResult = new SignInResult(token);
        SignInResponse body = new SignInResponse();

        body.setContent(signInResult);
        body.setStatusCode(200);
        body.setMessage("Logged In");

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_OK);

        response.getOutputStream().write(objectMapper.writeValueAsBytes(body));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {

        SignInResponse body = new SignInResponse();

        body.setStatusCode(400);
        body.setMessage("Log In Failed");

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        response.getOutputStream().write(objectMapper.writeValueAsBytes(body));
    }
}
