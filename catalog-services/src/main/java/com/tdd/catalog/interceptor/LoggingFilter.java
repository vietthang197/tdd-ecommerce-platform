package com.tdd.catalog.interceptor;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class LoggingFilter extends OncePerRequestFilter {

    private final Logger logger = LogManager.getLogger();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        long startTime = System.currentTimeMillis();
        filterChain.doFilter(requestWrapper, responseWrapper);
        long timeTaken = System.currentTimeMillis() - startTime;

        if (MediaType.APPLICATION_JSON_VALUE.equals(requestWrapper.getContentType())) {
            String requestBody = getStringValue(requestWrapper.getContentAsByteArray(),
                    request.getCharacterEncoding());
            String responseBody = getStringValue(responseWrapper.getContentAsByteArray(),
                    response.getCharacterEncoding());

            logger.info(
                    "PATH={}; METHOD={}; REQUEST PAYLOAD={}; RESPONSE CODE={}; RESPONSE={}; TIME TAKEN={}",
                    request.getRequestURI(), request.getMethod(), requestBody, response.getStatus(), responseBody,
                    timeTaken);
        } else {
            logger.info(
                    "PATH={}; METHOD={}; RESPONSE CODE={}; TIME TAKEN={}",
                    request.getRequestURI(), request.getMethod(), response.getStatus(),
                    timeTaken);
        }
        responseWrapper.copyBodyToResponse();
    }

    private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
        try {
            return new String(contentAsByteArray, characterEncoding).lines().parallel().collect(Collectors.joining(" "));
        } catch (UnsupportedEncodingException e) {
            logger.error(e);
        }
        return "";
    }
}
