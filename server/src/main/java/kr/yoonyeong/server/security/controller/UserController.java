package kr.yoonyeong.server.security.controller;

import kr.yoonyeong.server.security.dto.*;
import kr.yoonyeong.server.security.service.UserService;
import kr.yoonyeong.server.security.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

/**
 * @author rival
 * @since 2022-08-03
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;



    @GetMapping
    public Authentication auth(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    // 회원가입
    @PostMapping("/sign-up")
    public ResponseEntity<?> registerUser(@RequestBody SignUpForm signUpForm){
        try {
            userService.create(signUpForm);
            SignUpResponse body = SignUpResponse.builder()
                .message("User account created")
                .statusCode(200)
                .build();
            return ResponseEntity.status(HttpStatus.CREATED).body(body);
        }catch(Exception e){
            SignUpResponse body = SignUpResponse.builder()
                .message("Invalid Form")
                .statusCode(404)
                .build();
            return ResponseEntity.badRequest().body(body);
        }
    }

}
