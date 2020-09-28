package com.greentower.api.core.security;

import com.google.common.net.HttpHeaders;
import com.greentower.api.rules.auth_user.service.impl.JwtUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtTokenProvider jwtTokenProvider;
    private JwtUserDetailsService jwtUserDetailsService;

    public JwtAuthenticationFilter(JwtTokenProvider tokenProvider, JwtUserDetailsService jwtUserDetailsService) {
        this.jwtTokenProvider = tokenProvider;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")){
            String token = authorizationHeader.replace("Bearer ", "");
            this.checkToken(token, new WebAuthenticationDetailsSource().buildDetails(request));
        }else{
            logger.error("Não foi possível encontrar o prefixo bearer, o Header vai ser ignorado.");
        }

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "authorization, content-type, xsrf-token, Cache-Control");
        response.addHeader("Access-Control-Expose-Headers", "xsrf-token");

        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private void checkToken(String token, WebAuthenticationDetails authDetails){
        try {
            Optional<String> email = jwtTokenProvider.getEmailFromToken(token);

            if (email.isPresent() && SecurityContextHolder.getContext().getAuthentication() == null){
                User user = (User) jwtUserDetailsService.loadUserByUsername(email.get());
                if (jwtTokenProvider.validateToken(token, user)){
                    UsernamePasswordAuthenticationToken authentication = jwtTokenProvider.getAuthentication(token, user);
                    authentication.setDetails(authDetails);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (IllegalArgumentException exception ){
            logger.error("An error occurred during while getting username from token.", exception);
        } catch (ExpiredJwtException exception){
            logger.warn("The token is expired and not valid anymore.");
        } catch (MalformedJwtException exception){
            logger.error("Authentication Failed. Unable to read JSON value.", exception);
        } catch (SignatureException exception){
            logger.error("Authentication Failed. Username or Password not valid.", exception);
        }
    }
}
