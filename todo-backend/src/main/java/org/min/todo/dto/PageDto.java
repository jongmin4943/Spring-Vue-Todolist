package org.min.todo.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageDto {

    @Builder.Default
    private int page = 1;
    @Builder.Default
    private int size = 10;

    private String keyword;

    public void setPage(int page) {
        this.page = page > 0 ? page : 1;
    }

    public void setSize(int size) {
        this.size = size < 10 ? size : 10;
    }


    /**
     * @Method Name : getPageable
     * @작성일 : 2021. 08. 30.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : page 정보를 토대로 Pageable 객체를 반환한다.
     * @return page정보가 담긴 Pageable 객체
     */
    @JsonIgnore
    public Pageable getPageable() {
        return PageRequest.of(this.page - 1, this.size);
    }

}
