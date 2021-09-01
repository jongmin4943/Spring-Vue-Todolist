package org.min.todo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.min.todo.dto.PageDto;
import org.min.todo.dto.todo.TodoDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TodoMapper {
    /**
     * @Method Name : save
     * @작성일 : 2021. 09. 01.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : Todo를 저장한다.
     * @param dto
     * @return 저장된 row의 갯수
     */
    int save(TodoDto dto);

    /**
     * @Method Name : modify
     * @작성일 : 2021. 09. 01.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : Todo의 tno를 기준으로 Todo의 정보를 수정한다.
     * @param dto
     * @return 수정된 row의 갯수
     */
    int modify(TodoDto dto);

    /**
     * @Method Name : done
     * @작성일 : 2021. 09. 01.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : tno의 done 상태를 스위치 시킨다.
     * @param tno
     * @return 수정된 row의 갯수
     */
    int done(Long tno);

    /**
     * @Method Name : getSearchedListWithUser
     * @작성일 : 2021. 09. 01.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : 해당 user의 todo를 PageDto에 있는 page와 size, keyword를 토대로 DB에서 조회한다.
     * @param pageDto 페이지 및 검색 정보
     * @param username
     * @return 검색 조건에 맞게 조회된 TodoDto의 List
     */
    List<TodoDto> getSearchedListWithUser(@Param("pageDto") PageDto pageDto, @Param("username") String username);

    /**
     * @Method Name : countAll
     * @작성일 : 2021. 09. 01.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : 검색 조건을 토대로 해당 user의 todo들의 갯수를 반환한다.
     * @param pageDto 페이지 및 검색 정보
     * @param username
     * @return 검색 조건에 맞게 조회된 row 갯수
     */
    long countAll(@Param("pageDto") PageDto pageDto, @Param("username") String username);

    /**
     * @Method Name : findById
     * @작성일 : 2021. 09. 01.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : tno를 기준으로 todo를 조회한다.
     * @param tno
     * @return 조회 된 TodoDto
     */
    TodoDto findById(Long tno);

    /**
     * @Method Name : delete
     * @작성일 : 2021. 09. 01.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : List에 tno들을 기준으로 todo를 삭제시킨다.
     * @param tnos 삭제하고 싶은 tno의 list
     * @return 삭제 된 row의 갯수
     */
    int delete(List<Long> tnos);
}

