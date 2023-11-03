package com.doran.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import com.doran.redis.balcklist.service.BlackListService;
import com.doran.redis.refresh.service.RefreshTokenService;
import com.doran.user.dto.req.UserTokenBaseDto;
import com.doran.user.service.CustomUserDetailService;
import com.doran.user.type.Roles;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class JwtProvider {

    @Value("${jwt.secret-key}")
    private String secretKey;

    private static final long accessTokenValidTime = 24 * 60 * 60 * 1000L;

    private static final long refreshTokenValidTime = 10 * 24 * 60 * 60 * 1000L;

    private final CustomUserDetailService customUserDetailService;
    private final RefreshTokenService refreshTokenService;
    private final BlackListService blackListService;

    //키 생성
    private static Key getSigningKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        SecretKey key = new SecretKeySpec(keyBytes, "HmacSHA256");
        return key;
    }

    //토큰생성 - 공통 코드
    public String createToken(UserTokenBaseDto dto, long time) {
        //토큰 제목
        log.info("토큰 생성하러 들어감");

        Claims claims = createClaims(dto, time);

        //value가 null인 경우 에러가 발생하지 않음
        //value가 null인 경우 그냥 jwt에 들어가지 않음
        //-> 부모, 아이 나눌 것 없이 유동적인 사용이 가능해보임
        claims.put("userRole", dto.getUserRole());
        claims.put("userId", dto.getUserId());
        claims.put("childId", dto.getChildId());
        claims.put("selectProfileId", dto.getSelectProfileId());

        return "Bearer " + Jwts.builder()
            .setClaims(claims)
            .signWith(getSigningKey(secretKey), SignatureAlgorithm.HS256)
            .compact();
    }

    public Claims createClaims(UserTokenBaseDto dto, long time) {
        Claims claims = Jwts.claims();
        Date now = new Date();

        claims
            .setSubject(Integer.toString(dto.getUserId()))
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + time));

        if (dto.getUserRole().equals(Roles.CHILD)) {
            claims
                .setExpiration(new Date(now.getTime() + (accessTokenValidTime * 365)));
        }

        return claims;
    }

    //엑세스 토큰 생성
    public String createAccessToken(UserTokenBaseDto dto) {
        return this.createToken(dto, accessTokenValidTime);
    }

    //리프레시 토큰 생성
    public String createRefreshToken(UserTokenBaseDto dto) {
        String refreshToken = this.createToken(dto, refreshTokenValidTime);

        refreshTokenService.save(refreshToken, dto.getUserId());

        return refreshToken;
    }

    //토큰 디코딩
    public Claims getUserInfo(String token) {
        JwtParser parser = Jwts.parserBuilder().setSigningKey(getSigningKey(secretKey)).build();

        //토큰에서 바디를 꺼내 payload의 sub만 꺼냈음 -> 유저 식별자를 통해 db를 조회하기 위함
        //payload의 내용이 충분히 있다면 db조회 과정을 생략하고 claim을 바로 사용해되나
        //payload에 많은 정보가 들어가는 것은 보안적으로 불리하며 식별자를 통해 db를 한 번 조회하는 것이 깔끔하고 정확하다고 판단
        return parser.parseClaimsJws(bearerRemove(token)).getBody();
    }

    private String bearerRemove(String token) {
        return token.substring("Bearer ".length());
    }

    //헤더에서 토큰 가져오기
    public String getAccessToken(HttpServletRequest request) {

        return request.getHeader("Authorization");
    }

    public String getRefreshToken(HttpServletRequest request) {

        return request.getHeader("RefreshToken");
    }

    //토큰 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = customUserDetailService.loadUserByUsername(this.getUserInfo(token));

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    //토큰 유효성 검증
    //유효성 검사 성공 true 실패 false
    public boolean isTokenValid(String token) {
        token = bearerRemove(token);

        try {
            Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey(secretKey))
                .build()
                .parseClaimsJws(token)
                .getBody();

            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    //블랙리스트 테이블에서 엑세스 토큰 조회
    // -> 조회될 경우 요청 불가능 -> 이미 로그아웃을 해버린 유저
    public void checkBlackList(String accessToken) {
        blackListService.findBlackList(accessToken);
    }
}
