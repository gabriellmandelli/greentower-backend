package com.softplayer.apply.infrastructure.security;

import com.google.common.net.HttpHeaders;
import com.softplayer.apply.main.service.UsuarioService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
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

    private UsuarioService usuarioService;

    private JwtTokenProvider jwtTokenProvider;


    @Autowired
    public JwtAuthenticationFilter(UsuarioService usuarioService, JwtTokenProvider tokenProvider) {
        this.usuarioService = usuarioService;
        this.jwtTokenProvider = tokenProvider;
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
                User user = (User) usuarioService.loadUserByUsername(email.get());
                if (jwtTokenProvider.validateToken(token, user)){
                    UsernamePasswordAuthenticationToken authentication = jwtTokenProvider.getAuthentication(token, user);
                    authentication.setDetails(authDetails);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (IllegalArgumentException exception){
            logger.error("Ocorreu um erro durante a busca pelo email a partir do token.", exception);
        } catch (ExpiredJwtException exception){
            logger.warn("O token está expirado e não está mais valido.");
        } catch (SignatureException exception){
            logger.error("Falha na autenticação. O email ou senha não são validos.", exception);
        }
    }
}
