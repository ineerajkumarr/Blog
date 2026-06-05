package com.project.blog.security;

import com.project.blog.exceptions.UnAuthenticatedException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.ArrayList;


public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();

        return path.startsWith("/users/") ||
                path.startsWith("/blogs");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, java.io.IOException {
        System.out.println("Request : "+request.getRequestURI());

        String header = request.getHeader("Authorization");
        System.out.println("header "+header);

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            try {
                String userId = JwtUtil.extractUserId(token);
                System.out.println("Authenticated user: " + userId);
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userId, null, new ArrayList<>());

                SecurityContextHolder.getContext().setAuthentication(auth);

                filterChain.doFilter(request, response);
            } catch (Exception e) {
                response.setStatus(403);
                response.setContentType("application/json");
                response.getWriter().write("{\"message\":\"User not logged in. Invalid Token.\"}");
                return;
            }
        }else{
            response.setStatus(403);
            response.setContentType("application/json");
            response.getWriter().write("{\"message\":\"Not an active session. User not logged in\"}");
            return;
        }
        //what  is this username and passwd authentication token

    }
}
