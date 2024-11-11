package com.elibrary.LibraLink.services;

import com.elibrary.LibraLink.entities.Category;
import com.elibrary.LibraLink.entities.ErrorLogs;
import com.elibrary.LibraLink.exceptions.CustomExceptions;
import com.elibrary.LibraLink.repositories.ErrorLogsRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import javax.naming.AuthenticationException;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

@Service
public class ErrorLogsService {

    private final ErrorLogsRepository errorLogsRepository;

    public ErrorLogsService(ErrorLogsRepository errorLogsRepository){
        this.errorLogsRepository = errorLogsRepository;
    }

    //CREATE ERROR LOGS
    public ErrorLogs addErrorLogs(Exception exception){

        Map<Class<? extends Exception>, String> exceptionStatusCodeMap = Map.ofEntries(
                Map.entry(IllegalArgumentException.class, "400"),                   // Bad Request
                Map.entry(CustomExceptions.ResourceNotFoundException.class, "404"), // Not Found
                Map.entry(NullPointerException.class, "500"),                       // Internal Server Error
                Map.entry(IllegalStateException.class, "500"),                      // Internal Server Error(I guest)
                Map.entry(TimeoutException.class, "408"),                           // Request Timeout
                Map.entry(AuthenticationException.class, "401"),                    // Unauthorized(Not Sure.Already Created StandAlone Class For That)
                Map.entry(AuthorizationDeniedException.class, "403"),               // Forbidden
                Map.entry(NumberFormatException.class, "400"),                      // Bad Request(Not Necessary)
                Map.entry(ConstraintViolationException.class, "400"),               // Bad Request (Validation errors)
                Map.entry(MethodArgumentNotValidException.class, "400"),            // Bad Request (Validation errors)
                Map.entry(DataIntegrityViolationException.class, "409"),            // Internal Server Error (Database constraints)
                Map.entry(SQLException.class, "500"),                               // Internal Server Error (SQL errors)
                Map.entry(HttpRequestMethodNotSupportedException.class, "405"),     // Method Not Allowed
                Map.entry(UnsupportedOperationException.class, "501"),              // Not Implemented
                Map.entry(NoSuchElementException.class, "404"),                     // Not Found (No such element)
                Map.entry(FileNotFoundException.class, "404"),                      // Not Found (File not found)
                Map.entry(InsufficientAuthenticationException.class, "401")
        );

        String statusCode = exceptionStatusCodeMap.getOrDefault(exception.getClass(),"500");

        ErrorLogs errorLogs = new ErrorLogs();
        errorLogs.setError_message(exception.getMessage());
        errorLogs.setError_happen_at(LocalDateTime.now());
        errorLogs.setError_type(exception.getClass().getName());
        errorLogs.setError_path(getStackTrace(exception));
        errorLogs.setFix_status(false);
        errorLogs.setStatus_code(statusCode);
        errorLogs.setStatus(true);
        errorLogs.setAdditional_info("No Additional Info");
        return errorLogsRepository.save(errorLogs);
    }

    //GET ERROR LOGS BY ID
    public Optional<ErrorLogs> findErrorLogsById(Integer id){
        return errorLogsRepository.findById(id);
    }

    //GET  ERROR LOGS
    public List<ErrorLogs> findAllErrorLogs(){
        return errorLogsRepository.findAll();
    }

    //UPDATE ERROR LOGS BY ID
    public ErrorLogs updateErrorLogs(ErrorLogs errorLogs){
        Optional<ErrorLogs> originalErrorLogs = errorLogsRepository.findById(errorLogs.getId());

        if(originalErrorLogs.isPresent()){
            ErrorLogs errorLogsForUpdate = originalErrorLogs.get();
            errorLogsForUpdate.setFix_status(!errorLogsForUpdate.isFix_status());
            errorLogsForUpdate.setAdditional_info(errorLogs.getAdditional_info());

            return errorLogsRepository.save(errorLogsForUpdate);
        }else {
            throw new Error("ErrorLogs Not Found With Id "+errorLogs.getId());
        }
    }

    //DELETE  ERROR LOGS (SOFT)
    public void softDeleteErrorLogs(Integer id){
        Optional<ErrorLogs> errorLogs = errorLogsRepository.findById(id);
        if(errorLogs.isPresent()){
            ErrorLogs errorLogsForDle = errorLogs.get();
            errorLogsForDle.setStatus(false);

            errorLogsRepository.save(errorLogsForDle);
        }else{
            throw new Error("ErrorLogs Not Found With Id "+id);
        }
    }

    //DELETE ERROR LOGS (HARD)   { I don't know this method need or not just write for ready when it's need }
    public void permanentDeleteErrorLogs(Integer id){
        Optional<ErrorLogs> errorLogs = errorLogsRepository.findById(id);
        if(errorLogs.isPresent()){
            errorLogsRepository.deleteById(id);
        }else{
            throw new Error("ErrorLogs Not Found With Id "+id);
        }
    }

    //GET STACK TRACE OF THE ERROR
    private String getStackTrace(Exception exception){
        StringBuilder stackTrace = new StringBuilder();
        for(StackTraceElement element : exception.getStackTrace()){
            stackTrace.append(element.toString()).append("/n");
        }
        return stackTrace.toString();
    }
}
