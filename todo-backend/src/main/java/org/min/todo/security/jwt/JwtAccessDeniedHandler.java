package org.min.todo.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.min.todo.exception.ExceptionResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class JwtAccessDeniedHandler implements AccessDeniedHandler {


    /**
     * @Method Name : handle
     * @작성일 : 2021. 08. 30.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : AccessDeny 가 되면 응답에 403에러와 함께 권한이 없다는걸 json 형태로 반환한다.
     * @param request
     * @param response
     * @param accessDeniedException
     * @throws IOException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setHeader("content-type", "application/json");
        ExceptionResponse dto = new ExceptionResponse(403, "권한이 없습니다.");
        OutputStream out = response.getOutputStream();
        ObjectMapper om = new ObjectMapper();
        om.writeValue(out, dto);
        out.flush();
        out.close();
    }

}
