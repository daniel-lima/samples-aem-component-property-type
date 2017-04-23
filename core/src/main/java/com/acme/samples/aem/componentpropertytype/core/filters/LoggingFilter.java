package com.acme.samples.aem.componentpropertytype.core.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.felix.scr.annotations.sling.SlingFilter;
import org.apache.felix.scr.annotations.sling.SlingFilterScope;
import org.apache.sling.api.SlingHttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple servlet filter component that logs incoming requests.
 */
@SlingFilter(order = -700, scope = SlingFilterScope.REQUEST)
public class LoggingFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response,
            final FilterChain filterChain) throws IOException, ServletException {

        final SlingHttpServletRequest slingRequest = (SlingHttpServletRequest) request;
        logger.debug("request for {}, with selector {}", slingRequest
                .getRequestPathInfo().getResourcePath(), slingRequest
                .getRequestPathInfo().getSelectorString());

        filterChain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void destroy() {}

}
