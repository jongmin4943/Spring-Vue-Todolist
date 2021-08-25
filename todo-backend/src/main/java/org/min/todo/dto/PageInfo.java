package org.min.todo.dto;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageInfo {
    private int page;
    private int size;
    private int totalCount;
    private List<Integer> pageList;
    private boolean prev, next;
    private int start,end;
    private String keyword;

    public PageInfo(int page, int size, int totalCount,String keyword) {
        this.page = page;
        this.size = size;
        this.totalCount = totalCount;
        this.keyword = keyword != null? keyword: "";
        // 총 페이지 수
        int totalPage = (int)(Math.ceil(totalCount/(double)size));

        // 현재 보고있는 페이지의 마지막 페이지
        int tempEnd = (int) (Math.ceil(page/10.0)) * 10;

        start = tempEnd - 9;

        // 현재 보고있는 마지막 페이지와 총 페이지 수 비교
        end = totalPage > tempEnd ? tempEnd : totalPage;

        prev = start > 1;

        // 현재 보고있는 마지막 페이지보다 총 페이지가 더 크면 다음이 있다.
        next = totalPage > tempEnd;

        // 현재 보고있는 페이지의 페이지 리스트들
        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());

    }

}
