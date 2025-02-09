package com.elibrary.LibraLink.exceptions;

import com.elibrary.LibraLink.services.ErrorLogsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint  {

    // CONSTANT VALUES
    private final ObjectMapper objectMapper;
    private final ErrorLogsService errorLogsService;

    // CONSTRUCTORS
    public CustomAuthenticationEntryPoint(ObjectMapper objectMapper, ErrorLogsService errorLogsService) {
        this.objectMapper = objectMapper;
        this.errorLogsService = errorLogsService;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ErrorResponse errorResponse = new ErrorResponse(
                "Unauthorized Access",
                        request.getRequestURI(),
                LocalDateTime.now(),
                HttpStatus.UNAUTHORIZED.value()
        );
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}