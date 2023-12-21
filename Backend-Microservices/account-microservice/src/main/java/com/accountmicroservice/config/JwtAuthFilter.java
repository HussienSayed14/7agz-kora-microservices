package com.accountmicroservice.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * The OncePerRequestFilter indicates that this filter will be called once when a request comes in.
 * This filter will be used to intercept all requests and check if the request has a valid JWT token.
 * If the token is valid, the request will be allowed to pass through.
 * If the token is invalid, the request will be blocked.
 * This filter will be added to the Spring Security filter chain.
 * The filter chain is a series of filters that are applied to the request.
 * */
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;



    @Override
    protected void doFilterInternal(
           @NonNull HttpServletRequest request,
           @NonNull HttpServletResponse response,
           @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        /** Get the authorization header from the request header */
        final String authHeader = request.getHeader("Authorization");

        final String jwtToken;
        final String userEmail;
        /** If the authorization header is null or does not start with "Bearer "
         * then the request will be allowed to pass through to the next filter in the filter chain.
          */
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        /** Get the JWT token from the authorization header */
        jwtToken = authHeader.substring(7);
        userEmail = jwtService.extractUserEmail(jwtToken);
        /** This means that the user is NOT already authenticated */
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails user = this.userDetailsService.loadUserByUsername(userEmail);
            /** If the JWT token is valid, then the user will be authenticated and update Security context*/
            if(jwtService.isTokenValid(jwtToken,user)){
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request,response);



    }
}
