package com.elibrary.LibraLink.services;

import com.elibrary.LibraLink.repositories.ErrorLogsRepository;
import org.springframework.stereotype.Service;

@Service
public class ErrorLogsService {

    private final ErrorLogsRepository errorLogsRepository;

    public ErrorLogsService(ErrorLogsRepository errorLogsRepository){
        this.errorLogsRepository = errorLogsRepository;
    }
}
