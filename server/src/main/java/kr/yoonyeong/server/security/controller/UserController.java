package kr.yoonyeong.server.security.controller;

import kr.yoonyeong.server.security.dto.UserDTO;
import kr.yoonyeong.server.security.dto.UserResponseDTO;
import kr.yoonyeong.server.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO){
        try {
            UserDTO createdUser = userService.create(userDTO);

            UserResponseDTO body = UserResponseDTO.builder()
                .userDTO(createdUser)
                .error(null)
                .build();

            return ResponseEntity.status(HttpStatus.CREATED).body(body);
        }catch(Exception e){
            UserResponseDTO body = UserResponseDTO.builder()
                .error(null)
                .build();
            return ResponseEntity.badRequest().body(body);
        }
    }


    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO){
        UserDTO foundUser = userService.getByCredentials(userDTO.getEmail(), userDTO.getPassword());
        if(foundUser != null){
            UserResponseDTO body = UserResponseDTO.builder()
                .userDTO(foundUser)
                .error(null)
                .build();
            return ResponseEntity.ok().body(body);
        }else{
            UserResponseDTO body = UserResponseDTO.builder()
                .error("Login Failed")
                .build();
            return ResponseEntity.badRequest().body(body);
        }

    }
}
