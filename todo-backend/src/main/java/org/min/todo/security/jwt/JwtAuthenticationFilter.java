package org.min.todo.security.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public final JwtTokenProvider jwtTokenProvider;


    /**
     * @Method Name : doFilterInternal
     * @작성일 : 2021. 08. 30.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : UsernamePasswordAuthenticationFilter 진입 전 JWT토큰을 검사해 정상적인 토큰이면 SecurityContextHolder에 해당 유저 정보를 넣는다.
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null && authorizationHeader.length() > 7) {
            try {
                String token = authorizationHeader.substring(7);
                if(jwtTokenProvider.validateToken(token)) {
                    UsernamePasswordAuthenticationToken authentication = jwtTokenProvider.getAuthentication(token);
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                log.error(e.getMessage(),e.getStackTrace());
            }
        }
        filterChain.doFilter(request, response);
    }
}
