package security.Interceptor;

import dev.dre.chatappserver.ChatAppServerApplication;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import security.service.TokenService;

@Configuration
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid or missing token");
            return false;
        }

        String actualToken = token.substring(7);

        if (ChatAppServerApplication.getTokenService().validateToken(actualToken)) return true;

        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid token");
        return false;
    }
}
