package kr.yoonyeong.server.controller;

import kr.yoonyeong.server.dto.TestRequestBodyDTO;
import kr.yoonyeong.server.dto.TestListResponseBodyDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rival
 * @since 2022-07-20
 */

@RestController
@RequestMapping("test")
public class TestController {
    @GetMapping
    public String testController(){
        return "Hello World!";
    }

    @GetMapping("/get")
    public String testGetController(){
        return "GET";
    }


    @GetMapping("/{id}")
    public String testPathVariablesController(@PathVariable(required = false) Integer id){
        return "Your Id is "+id;
    }

    @GetMapping("/query")
    public String testQueryController(@RequestParam(
        required = false
    ) Integer id){
        return id == null ? "Empty Query" : "Your Id is "+id;
    }


    @PostMapping("/body-req")
    public TestListResponseBodyDTO testBodyReqController(@RequestBody TestRequestBodyDTO testRequestBodyDTO){
        List<String> list = new ArrayList<>();
        list.add("Hello World");
        list.add("Nice to meet you");
        list.add(testRequestBodyDTO.getMessage());
        return TestListResponseBodyDTO.builder()
            .data(list)
            .build();
    }


    @GetMapping("/body-res")
    public TestListResponseBodyDTO testBodyResController(){
        List<String> list = new ArrayList<>();

        list.add("Hello World");
        list.add("Nice to meet you");

        return TestListResponseBodyDTO.builder()
            .data(list)
            .build();
    }

    @GetMapping("/body-error")
    public ResponseEntity<?> testBodyErrorResController(){
        List<String> list = new ArrayList<>();
        list.add("Error Message...");

        TestListResponseBodyDTO res = TestListResponseBodyDTO.builder()
            .error("Error")
            .data(list)
            .build();

        return ResponseEntity.badRequest().body(res);
    }





}
