package com.elibrary.LibraLink.services;

import com.elibrary.LibraLink.repositories.LoginHistoryRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginHistoryService {

    private final LoginHistoryRepository loginHistoryRepository;

    public LoginHistoryService(LoginHistoryRepository loginHistoryRepository){
        this.loginHistoryRepository = loginHistoryRepository;
    }
}
