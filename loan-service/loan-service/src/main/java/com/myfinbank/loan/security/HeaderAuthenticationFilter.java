package com.myfinbank.loan.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.List;

@Component
public class HeaderAuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String email = request.getHeader("X-User-Email");
        String role = request.getHeader("X-User-Role");
        if (email != null && role != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            String normalizedRole = role.startsWith("ROLE_") ? role : "ROLE_" + role;
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    email,
                    null,
                    List.of(new SimpleGrantedAuthority(normalizedRole))
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
