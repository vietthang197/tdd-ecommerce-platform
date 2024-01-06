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

        String requestId = requestWrapper.getHeader("X-Request-Id");
        String clientIP = getClientIP(requestWrapper);
        if (MediaType.APPLICATION_JSON_VALUE.equals(requestWrapper.getContentType())) {
            String requestBody = getStringValue(requestWrapper.getContentAsByteArray(),
                    request.getCharacterEncoding());
            String responseBody = getStringValue(responseWrapper.getContentAsByteArray(),
                    response.getCharacterEncoding());

            logger.info(
                    "RequestId={}, IP={}, Path={}; Method={}; RequestPayload={}; ResponseCode={}; Response={}; TimeTaken={}",
                    requestId, clientIP, request.getRequestURI(), request.getMethod(), requestBody, response.getStatus(), responseBody,
                    timeTaken);
        } else {
            logger.info(
                    "RequestId={}, IP={}, Path={}; Method={}; ResponseCode={}; TimeTaken={}",
                    requestId, clientIP, request.getRequestURI(), request.getMethod(), response.getStatus(),
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

    private String getClientIP(ContentCachingRequestWrapper request) {
        String clientIP = request.getHeader("X-Forwarded-For");
        if (clientIP == null || clientIP.isEmpty() || "unknown".equalsIgnoreCase(clientIP)) {
            clientIP = request.getHeader("Proxy-Client-IP");
        }
        if (clientIP == null || clientIP.isEmpty() || "unknown".equalsIgnoreCase(clientIP)) {
            clientIP = request.getHeader("WL-Proxy-Client-IP");
        }
        if (clientIP == null || clientIP.isEmpty() || "unknown".equalsIgnoreCase(clientIP)) {
            clientIP = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (clientIP == null || clientIP.isEmpty() || "unknown".equalsIgnoreCase(clientIP)) {
            clientIP = request.getHeader("HTTP_X_FORWARDED");
        }
        if (clientIP == null || clientIP.isEmpty() || "unknown".equalsIgnoreCase(clientIP)) {
            clientIP = request.getHeader("HTTP_X_CLUSTER_CLIENT_IP");
        }
        if (clientIP == null || clientIP.isEmpty() || "unknown".equalsIgnoreCase(clientIP)) {
            clientIP = request.getHeader("HTTP_CLIENT_IP");
        }
        if (clientIP == null || clientIP.isEmpty() || "unknown".equalsIgnoreCase(clientIP)) {
            clientIP = request.getHeader("HTTP_FORWARDED_FOR");
        }
        if (clientIP == null || clientIP.isEmpty() || "unknown".equalsIgnoreCase(clientIP)) {
            clientIP = request.getHeader("HTTP_FORWARDED");
        }
        if (clientIP == null || clientIP.isEmpty() || "unknown".equalsIgnoreCase(clientIP)) {
            clientIP = request.getRemoteAddr();
        }
        return clientIP;
    }
}
