package com.elibrary.LibraLink.exceptions;

import com.elibrary.LibraLink.services.ErrorLogsService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    // CONSTANT VALUES
    private final ErrorLogsService errorLogsService;

    // CONSTRUCTOR
    public GlobalExceptionHandler(ErrorLogsService errorLogsService) {
        this.errorLogsService = errorLogsService;
    }

    //RESOURCE NOT FOUND EXCEPTION
    @ExceptionHandler(CustomExceptions.ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(CustomExceptions.ResourceNotFoundException ex, WebRequest request) {
        errorLogsService.addErrorLogs(ex);
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                request.getDescription(false),
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    //BAD REQUEST EXCEPTION
    @ExceptionHandler(CustomExceptions.BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(CustomExceptions.BadRequestException ex, WebRequest request) {
        errorLogsService.addErrorLogs(ex);
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                request.getDescription(false),
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    //UNAUTHORIZED EXCEPTION (OPTIONAL)
    @ExceptionHandler(CustomExceptions.UnAuthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnAuthorized(CustomExceptions.UnAuthorizedException ex, WebRequest request) {
        errorLogsService.addErrorLogs(ex);
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                request.getDescription(false),
                LocalDateTime.now(),
                HttpStatus.UNAUTHORIZED.value()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    //FORBIDDEN EXCEPTION
    @ExceptionHandler(CustomExceptions.ForbiddenException.class)
    public ResponseEntity<ErrorResponse> handleForbidden(CustomExceptions.ForbiddenException ex, WebRequest request) {
        errorLogsService.addErrorLogs(ex);
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                request.getDescription(false),
                LocalDateTime.now(),
                HttpStatus.FORBIDDEN.value()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    //HTTP STATUS CODE EXCEPTION (TO CATCH OTHER EXCEPTION SUB CLASS OF HTTP STATUS CODE EXCEPTION)
    @ExceptionHandler(HttpStatusCodeException.class)
    public ResponseEntity<ErrorResponse> handleHttpStatusCodeException(HttpStatusCodeException ex, WebRequest request) {
        errorLogsService.addErrorLogs(ex);
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                request.getDescription(false),
                LocalDateTime.now(),
                ex.getStatusCode().value()
        );
        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }


    //DATA INTEGRITY VIOLATION EXCEPTION (SQL ERROR)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request) {
        errorLogsService.addErrorLogs(ex);

        ErrorResponse errorResponse = new ErrorResponse(
                "Database constraint violation: " + ex.getMostSpecificCause().getMessage(),
                request.getDescription(false),
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // EXCEPTION (TO CATCH ALL EXCEPTION)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllException(Exception ex, WebRequest request) {
        errorLogsService.addErrorLogs(ex);
        System.out.println(request.getDescription(false).replaceFirst("uri=",""));
        ErrorResponse errorResponse = new ErrorResponse(
                "Exception occurred: " + ex.getMessage().replaceAll("/",""),
                request.getDescription(false).replaceFirst("uri=",""),
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
