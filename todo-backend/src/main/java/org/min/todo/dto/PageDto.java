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

    public int getSkip() {
        return (this.page-1) * this.size;
    }


}
