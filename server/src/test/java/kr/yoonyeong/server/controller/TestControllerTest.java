package kr.yoonyeong.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.yoonyeong.server.dto.TestRequestBodyDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author rival
 * @since 2022-07-20
 */

@WebMvcTest(controllers = TestController.class)
class TestControllerTest {
    @Autowired
    private MockMvc mvc;

    @DisplayName("1. TestController 문자열 response 테스트")
    @Test
    public void controller_test1() throws Exception {

        String content = "Hello World!";
        mvc.perform(get("/test"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(content));
    }

    @DisplayName("2. TestController JSON response 테스트")
    @Test
    public void controller_test2() throws Exception {
        mvc.perform(
            get("/test/body-res")
        )
            .andDo(print())
            .andExpect(
                jsonPath("$.messages").isArray()
            )
            .andExpect(
                jsonPath("$.error").isEmpty()
            );
    }

    @DisplayName("3. TestController JSON request 테스트")
    @Test
    public void controller_test3() throws Exception {
        TestRequestBodyDTO body = TestRequestBodyDTO.builder()
            .id(123)
            .message("Good Morning").build();

        String content = new ObjectMapper().writeValueAsString(body);

        mvc.perform(post("/test/body-req")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
            .andDo(
                print()
            );

    }

}