package com.zerobase.trade.security.token;


import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zerobase.trade.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import static com.zerobase.trade.exception.ErrorCode.ALREADY_REGISTER_EMAIL;
import static com.zerobase.trade.exception.ErrorCode.NOT_VALID_TOKEN;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,FilterChain filterChain
    ) throws ServletException, IOException {

        String token = jwtAuthenticationProvider.resolveToken(request);

        if (token != null) {
            if (jwtAuthenticationProvider.validateToken(token)) {
                Authentication auth = jwtAuthenticationProvider.getAuthentication(token); // Create Authentication object
                SecurityContextHolder.getContext().setAuthentication(auth); // Authenticate through SecurityContextHolder
            } else {
                throw new CustomException(NOT_VALID_TOKEN);
            }
        }

        filterChain.doFilter(request, response);
    }
}
