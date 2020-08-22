package com.softplayer.apply.infrastructure.security;

import com.softplayer.apply.infrastructure.config.JwtConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtTokenProvider {

    @Autowired
    private JwtConfig jwtConfig;

    private String AUTHORITIES_KEY = "scopes";

    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser()
                .setSigningKey(jwtConfig.getSigningKey())
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpirado(String token) {
        try {
            return LocalDateTime.now().isAfter(getAllClaimsFromToken(token).getExpiration().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        } catch (Exception exception) {
            return true;
        }
    }

    public String generateTokenByUsuario(User user){
        Date dataExpiracao = Date.from(LocalDateTime.now().plusMinutes(jwtConfig.getExpirationTimeMinutes()).atZone(ZoneId.systemDefault()).toInstant());

        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim(AUTHORITIES_KEY,user.getAuthorities())
                .signWith(SignatureAlgorithm.HS256, jwtConfig.getSigningKey())
                .setIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(dataExpiracao)
                .compact();
    }

    public Optional<String> getEmailFromToken(String token){
        return Optional.ofNullable(getAllClaimsFromToken(token).getSubject());
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token, User user){
        return new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
    }

    public Boolean validateToken(String token, User user){
        return this.getEmailFromToken(token).map( email -> email.equals(user.getUsername()) && !isTokenExpirado(token)).orElse(false);
    }
}
