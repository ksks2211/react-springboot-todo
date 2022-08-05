package kr.yoonyeong.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author rival
 * @since 2022-08-03
 */
@Builder
@AllArgsConstructor
@Data
public class TodoListRequestDTO {

    private int page;
    private int size;

    public TodoListRequestDTO(){
        this.page=0;
        this.size=10;
    }

    public Pageable getPageable(){
        return getPageable(
            Sort.by("createdAt").descending()
        );
    }

    public Pageable getPageable(Sort sort){
        return PageRequest.of(
            this.page,
            Math.min(this.size, 20),
            sort);
    }
}
