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
    public final static long TOKEN_VALIDATION_SECOND = 1000L * 60 * 15;

    private final PrincipalDetailsService principalDetailsService;

    public JwtTokenProvider (PrincipalDetailsService principalDetailsService) {
        this.principalDetailsService = principalDetailsService;
    }

    // apc.yml에 설정함
    @Value("${app.jwt.secret}")
    private String jwtSecret;

    // JWT 생성 파트
    public String doGenerateToken(String username,List<String> roles) {

        // getPrincipal PrincipalDetails담겨있는 정보를 가져옴

        Date now = new Date();
        Date expired = new Date(now.getTime() + TOKEN_VALIDATION_SECOND);

        return Jwts.builder()
                .claim("username",username)
                .claim("roles",roles) //
                .setIssuedAt(new Date())
                .setExpiration(expired) // 위 세개 PAYLOAD
                .signWith(SignatureAlgorithm.HS512, jwtSecret) // HEADER
                .compact(); // 만듬
    }

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

    public Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }

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
