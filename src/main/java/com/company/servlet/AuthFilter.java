package com.company.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@WebFilter("/*")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("/login", "/register")));

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length()).replaceAll("[/]+$", "");

        if (ALLOWED_PATHS.contains(path)) {
            filterChain.doFilter(httpRequest, httpResponse);
        } else {
            if (httpRequest.getSession().getAttribute("authorized") == null ||
                    httpRequest.getSession().getAttribute("authorized").equals("false")) {
                httpResponse.sendRedirect(httpRequest.getServletContext().getContextPath() + "/login");
            } else {
                filterChain.doFilter(httpRequest, httpResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
