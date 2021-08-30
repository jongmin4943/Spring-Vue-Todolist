package org.min.todo.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.min.todo.exception.ExceptionResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * @Method Name : commence
     * @작성일 : 2021. 08. 30.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : jwt가 없으면 401에러와 함께 로그인 해주세요 라는 메세지를 담아 반환한다.
     * @param request
     * @param response
     * @param authException
     * @throws IOException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader("content-type", "application/json");
        ExceptionResponse dto = new ExceptionResponse(401, "로그인 해주세요");
        OutputStream out = response.getOutputStream();
        ObjectMapper om = new ObjectMapper();
        om.writeValue(out, dto);
        out.flush();
        out.close();
    }

}