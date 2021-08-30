package org.min.todo.security.jwt;

import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.min.todo.security.principal.PrincipalDetails;
import org.min.todo.security.principal.PrincipalDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Log4j2
public class JwtTokenProvider {
    public final static long TOKEN_VALIDATION_SECOND = 1000L * 60 * 20; //20분

    private final PrincipalDetailsService principalDetailsService;

    public JwtTokenProvider (PrincipalDetailsService principalDetailsService) {
        this.principalDetailsService = principalDetailsService;
    }

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    /**
     * @Method Name : doGenerateToken
     * @작성일 : 2021. 08. 30.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : access 토큰을 생성한다.
     * @param username
     * @param roles
     * @return
     */
    public String doGenerateToken(String username,List<String> roles) {

        Date now = new Date();
        Date expired = new Date(now.getTime() + TOKEN_VALIDATION_SECOND);

        return Jwts.builder()
                .claim("username",username)
                .claim("roles",roles) //
                .setIssuedAt(new Date())
                .setExpiration(expired)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /**
     * @Method Name : validateToken
     * @작성일 : 2021. 08. 30.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : Token이 정상적인지 확인한다.
     * @param authToken
     * @return boolean
     */
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            log.error("claims에서 오류로 검증이 실패");
        } catch (MalformedJwtException ex) {
            log.error("구조적인 문제가 있는 JWToken");
        } catch (ExpiredJwtException ex) {
            log.error("유효기간 지난 JWToken");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWToken");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

    /**
     * @Method Name : extractAllClaims
     * @작성일 : 2021. 08. 30.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : 토큰의 body 부분을 추출한다.
     * @param token
     * @return
     */
    public Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * @Method Name : getAuthentication
     * @작성일 : 2021. 08. 30.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : 토큰을 이용해 UsernamePasswordAuthenticationToken객체를 생성한다.
     * @param accessToken
     * @return UsernamePasswordAuthenticationToken
     */
    public UsernamePasswordAuthenticationToken getAuthentication(String accessToken) {
        Claims claims = extractAllClaims(accessToken);
        String username = claims.get("username",String.class);
        List<String> roles = claims.get("roles", List.class);
        List<GrantedAuthority> authorities =
                roles == null || roles.size() == 0 ? Collections.emptyList() :
                roles.stream().map((role)->new SimpleGrantedAuthority("ROLE_"+role))
                .collect(Collectors.toList());

        PrincipalDetails principalDetails = new PrincipalDetails(username,authorities);
        return new UsernamePasswordAuthenticationToken(principalDetails, "", authorities);
    }
}
