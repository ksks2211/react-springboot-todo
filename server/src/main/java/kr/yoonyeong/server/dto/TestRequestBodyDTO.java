package kr.yoonyeong.server.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author rival
 * @since 2022-07-20
 */

@Data
@Builder
public class TestRequestBodyDTO {
    private Integer id;
    private String message;
}
