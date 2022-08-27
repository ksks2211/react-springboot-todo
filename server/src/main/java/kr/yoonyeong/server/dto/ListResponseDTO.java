package kr.yoonyeong.server.dto;

import lombok.*;

import java.util.List;

/**
 * @author rival
 * @since 2022-07-19
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ListResponseDTO<T> {

    @Getter(value = AccessLevel.NONE)
    protected List<T> data;

    String error;

}
