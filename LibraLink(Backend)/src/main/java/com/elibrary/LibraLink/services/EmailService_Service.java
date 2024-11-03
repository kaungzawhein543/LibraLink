package com.elibrary.LibraLink.services;

import com.elibrary.LibraLink.repositories.EmailServiceRepository;
import org.springframework.stereotype.Service;

@Service
public class EmailService_Service {

    private final EmailServiceRepository emailServiceRepository;

    public EmailService_Service(EmailServiceRepository emailServiceRepository){
        this.emailServiceRepository = emailServiceRepository;
    }
}
