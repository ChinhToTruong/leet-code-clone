package com.zev.application.service;

import com.zev.application.common.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {

    @Value("${authentication.secret-key.access-token}")
    private String ACCESS_TOKEN_SECRET_KEY;

    @Value("${authentication.secret-key.refresh-token}")
    private String REFRESH_TOKEN_SECRET_KEY;

    @Value("${authentication.time-expired.access-token}")
    private Long ACCESS_TOKEN_EXPIRED_SECRET_KEY;

    @Value("${authentication.time-expired.refresh-token}")
    private Long REFRESH_TOKEN_EXPIRED_SECRET_KEY;

    private final Map<String, String> secretMap = new HashMap<>();
    private final Map<String, Long> expirationMap = new HashMap<>();

    @PostConstruct
    public void init() {
        secretMap.put(Constants.TokenType.ACCESS_TOKEN, ACCESS_TOKEN_SECRET_KEY);
        secretMap.put(Constants.TokenType.REFRESH_TOKEN, REFRESH_TOKEN_SECRET_KEY);

        expirationMap.put(Constants.TokenType.ACCESS_TOKEN, ACCESS_TOKEN_EXPIRED_SECRET_KEY);
        expirationMap.put(Constants.TokenType.REFRESH_TOKEN, REFRESH_TOKEN_EXPIRED_SECRET_KEY);
    }

    public String genTimeExpiredJwt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DateTimeFormat.DATE_TIME_FORMAT);
        return LocalDateTime.now().plusHours(expirationMap.get(Constants.TokenType.ACCESS_TOKEN)).format(formatter);
    }

    public String generateAccessToken(String email) { // Use email as username
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, email, Constants.TokenType.ACCESS_TOKEN);
    }

    public String generateRefreshToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, email, Constants.TokenType.REFRESH_TOKEN);
    }


    private String createToken(Map<String, Object> claims, String email, String tokenType) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMap.get(tokenType) * 60 * 60 * 60 * 1000))
                .signWith(getSignKey(tokenType), SignatureAlgorithm.HS256)
                .compact();
    }


    private Key getSignKey(String tokenType) {
        byte[] keyBytes = Decoders.BASE64.decode(secretMap.get(tokenType));
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token, String tokenType) {
        return extractClaim(token, Claims::getSubject, tokenType);
    }

    public Date extractExpiration(String token, String tokenType) {
        return extractClaim(token, Claims::getExpiration, tokenType);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver, String tokenType) {
        final Claims claims = extractAllClaims(token, tokenType);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token, String tokenType) {
        return Jwts.parser()
                .setSigningKey(getSignKey(tokenType))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token, String tokenType) {
        return extractExpiration(token, tokenType).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails, String tokenType) {
        final String username = extractUsername(token, tokenType);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token, tokenType));
    }
}
