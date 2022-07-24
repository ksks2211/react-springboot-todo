package kr.yoonyeong.server.dto;

import lombok.Builder;

import java.util.List;

/**
 * @author rival
 * @since 2022-07-20
 */


public class TestResponseBodyDTO extends ResponseDTO<String>{
    public List<String> getMessages(){
        return this.data;
    }

    @Builder
    public TestResponseBodyDTO(List<String> data, String error){
        super(data,error);
    }
}
