package org.min.todo.repository.querydsl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TodoSearch {

    Page<Object[]> getSearchedList(String keyword, Pageable pageable);

    /**
     * @Method Name : getSearchedListWithUser
     * @작성일 : 2021. 08. 30.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : 요청 대상의 검색된 todo 리스트를 반환한다.
     * @param keyword 검색어
     * @param pageable 페이지 정보
     * @param username 요청 대상의 ID
     * @return Page타입의 요청 결과 Object[] 이며 0번째는 todo, 1번째는 user 정보가 들어있다.
     */
    Page<Object[]> getSearchedListWithUser(String keyword, Pageable pageable, String username);

}
