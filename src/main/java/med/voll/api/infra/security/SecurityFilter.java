package med.voll.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var tokenJWT = recuperarToken(request);
        System.out.println(tokenJWT);

        filterChain.doFilter(request, response); //Necessário para que o filter seja executado após a requisição.
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null ||!authorizationHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Token não encontrado");
        }
        return authorizationHeader.substring(7);
    }
}
