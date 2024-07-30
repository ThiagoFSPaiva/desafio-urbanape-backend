package br.com.thiagofspaiva.urbanape.security;

import br.com.thiagofspaiva.urbanape.modules.authentication.services.TokenService;
import br.com.thiagofspaiva.urbanape.modules.user.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;


@Component
public class SecurityFilter extends OncePerRequestFilter {

    final private TokenService tokenService;
    final private UserRepository userRepository;

    public SecurityFilter(TokenService tokenService, UserRepository userRepository){
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = recoverToken(request);
        if (token != null) {
            var userId = tokenService.validateToken(token);
            authenticateUser(userId);
        }
        filterChain.doFilter(request, response);
    }

    private void authenticateUser(String userId) {
        if (userId == null || userId.isEmpty()) return;

        var user = userRepository.findById(UUID.fromString(userId)).orElse(null);
        if (user == null) return;

        var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
