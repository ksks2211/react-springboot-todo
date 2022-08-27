package kr.yoonyeong.server.dto;

import lombok.Builder;

import java.util.List;

/**
 * @author rival
 * @since 2022-07-20
 */


public class TestListResponseBodyDTO extends ListResponseDTO<String> {
    public List<String> getMessages(){
        return this.data;
    }

    @Builder
    public TestListResponseBodyDTO(List<String> data, String error){
        super(data,error);
    }
}
