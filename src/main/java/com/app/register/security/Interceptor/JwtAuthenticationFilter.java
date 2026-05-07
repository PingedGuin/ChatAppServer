package com.app.register.security.Interceptor;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import com.app.register.security.auth.TokenService;
import jakarta.servlet.http.Cookie;

import java.io.IOException;
import java.util.List;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    public JwtAuthenticationFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String jwt = null;

        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            jwt = header.substring(7);
        }
        if (request.getCookies() != null) {
            for (Cookie c : request.getCookies()) {
                if ("jwt".equals(c.getName())) {
                    jwt = c.getValue();
                    break;
                }
            }
        }

        if (jwt == null) {
            filterChain.doFilter(request, response);
            return;
        }

        if (!tokenService.validateToken(jwt)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
            return;
        }

        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken("com/app/user", null, List.of());

        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return path.startsWith("/api/auth/");
    }

}
