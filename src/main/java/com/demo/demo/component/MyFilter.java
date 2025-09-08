package com.demo.demo.component;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info(" dofilter Running... ");
        filterChain.doFilter(servletRequest,servletResponse);
        log.info(" dofilter completed... ");

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
