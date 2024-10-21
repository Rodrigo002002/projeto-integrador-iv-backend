package com.apiathletevision.apiathletevision.helpers;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class PublicRoutes {
    public static final RequestMatcher PUBLIC_URLS = new OrRequestMatcher(
            new AntPathRequestMatcher("/", "GET"),
            new AntPathRequestMatcher("/index.html"),
            new AntPathRequestMatcher("/actuator/**", "GET"),
            new AntPathRequestMatcher("/assets/**"),
            new AntPathRequestMatcher("/swagger-ui/**", "GET"),
            new AntPathRequestMatcher("/api-docs", "GET"),
            new AntPathRequestMatcher("/api-docs/**", "GET"),
            new AntPathRequestMatcher("/api/auth/login", "POST"),
            new AntPathRequestMatcher("/api/auth/logout", "POST"),
            new AntPathRequestMatcher("/api/auth/check-token", "POST"),
            new AntPathRequestMatcher("/api/auth/refresh-token", "POST"),
            new AntPathRequestMatcher("/**", "PUT"),
            new AntPathRequestMatcher("/**", "GET"),
            new AntPathRequestMatcher("/**", "DELETE"),
            new AntPathRequestMatcher("/**", "POST")
    );

    public static boolean isPublic(HttpServletRequest request) {
        return PUBLIC_URLS.matches(request);
    }
}
