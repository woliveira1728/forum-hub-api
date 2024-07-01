package com.woliveira.forum_hub_api.infra.security;

import com.woliveira.forum_hub_api.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recoverToken(req);

        if (tokenJWT != null) {
            var sub = tokenService.getSubject(tokenJWT);
            var user = userRepository.findByEmail(sub);
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(req, res);
    }

    private String recoverToken(HttpServletRequest req) {
        var authorization = req.getHeader("Authorization");
        if (authorization != null) {
            return authorization.replace("Bearer ", "");
        }

        return null;
    }

}
