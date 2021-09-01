package org.min.todo.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
        this.size = size > 0 ? size : 10;
    }

    /**
     * @Method Name : getSkip
     * @작성일 : 2021. 09. 01.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : mybatis의 limit 첫번째 인자값 계산을 위한 함수
     * @return limit에 들어갈 숫자를 계산한 값
     */
    public int getSkip() {
        return (this.page-1) * this.size;
    }


}
