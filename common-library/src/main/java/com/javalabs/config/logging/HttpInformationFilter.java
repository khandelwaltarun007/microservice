package com.javalabs.config.logging;
import java.io.IOException;
import java.util.Enumeration;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.javalabs.config.ApplicationProperties;
import com.javalabs.config.requestfilter.RequestBusinessHeadersWrapper;
import com.javalabs.config.tracing.AccessTokenLogger;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
@Order(1000)
public class HttpInformationFilter implements Filter {
    private static final String RETURN_CODE = "returnCode";

    private final ApplicationProperties applicationProperties;
    private final AccessTokenLogger accessTokenLogger;
    private final RequestBusinessHeadersWrapper requestBusinessHeadersWrapper;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        try {

            MDC.put("httpMethod", httpServletRequest.getMethod());
            MDC.put("endPoint", StringUtils.difference(httpServletRequest.getContextPath(), httpServletRequest.getRequestURI()));

            accessTokenLogger.putTokenInfoToMDC();
            handleHeaders(httpServletRequest);
            requestBusinessHeadersWrapper.extractBusinessHeaders(httpServletRequest);
            MDC.put("business_headers", String.join("|", RequestBusinessHeadersWrapper.getBusinessHeaders()));
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            onResponse(httpServletResponse);
            requestBusinessHeadersWrapper.clear();
        }
    }

    public void onResponse(HttpServletResponse httpServletResponse) {
        MDC.put(RETURN_CODE, String.valueOf(httpServletResponse.getStatus()));
        log.info("Request has been completed with the status code of : " + httpServletResponse.getStatus());
    }

    private void handleHeaders(HttpServletRequest httpServletRequest) {
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                for (String taggableHeader : applicationProperties.getTaggableHeaders()) {
                    if (taggableHeader.equalsIgnoreCase(headerName.toLowerCase())) {
                        MDC.put(headerName, taggableHeader);
                    }
                }
            }
        }
    }
}
