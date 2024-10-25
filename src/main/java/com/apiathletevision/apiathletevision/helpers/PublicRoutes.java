package com.apiathletevision.apiathletevision.helpers;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class PublicRoutes {
    public static final RequestMatcher PUBLIC_URLS = new OrRequestMatcher(
            new AntPathRequestMatcher("/", "GET"),
            new AntPathRequestMatcher("/manifest.json", "GET"),
            new AntPathRequestMatcher("/index.html"),
            new AntPathRequestMatcher("/actuator/**", "GET"),
            new AntPathRequestMatcher("/assets/**"),
            new AntPathRequestMatcher("/favicon.ico", "GET"),
            new AntPathRequestMatcher("/swagger-ui/**", "GET"),
            new AntPathRequestMatcher("/v3/api-docs", "GET"),
            new AntPathRequestMatcher("/v3/api-docs/**", "GET"),
            new AntPathRequestMatcher("/api", "GET"),
            new AntPathRequestMatcher("/api/auth/login", "POST"),
            new AntPathRequestMatcher("/api/auth/login", "OPTIONS"),
            new AntPathRequestMatcher("/api/auth/login", "GET"),
            new AntPathRequestMatcher("/api/auth/logout", "POST"),
            new AntPathRequestMatcher("/api/auth/check-token", "POST"),
            new AntPathRequestMatcher("/api/auth/refresh-token", "POST"),

            //Rotas front
            new AntPathRequestMatcher("/auth/login", "GET"),
            new AntPathRequestMatcher("/auth/login", "POST"),
            new AntPathRequestMatcher("/auth/login", "OPTIONS")
    );

    public static boolean isPublic(HttpServletRequest request) {
        return PUBLIC_URLS.matches(request);
    }
}
