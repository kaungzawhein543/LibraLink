package com.elibrary.LibraLink.services;

import com.elibrary.LibraLink.entities.LoginHistory;
import com.elibrary.LibraLink.repositories.LoginHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoginHistoryService {

    // CONSTANT VALUES
    private final LoginHistoryRepository loginHistoryRepository;

    // CONSTRUCTOR
    public LoginHistoryService(LoginHistoryRepository loginHistoryRepository){
        this.loginHistoryRepository = loginHistoryRepository;
    }

    // CREATE LOGIN HISTORY
    public LoginHistory addLoginHistory(LoginHistory loginHistory){
        return loginHistoryRepository.save(loginHistory);
    }

    // GET LOGIN HISTORY BY ID
    public Optional<LoginHistory> findLoginHistoryById(UUID id){
        return loginHistoryRepository.findById(id);
    }

    // GET ALL LOGIN HISTORIES
    public List<LoginHistory> findAllLoginHistories(){
        return loginHistoryRepository.findAll();
    }


    // DELETE LOGIN HISTORIES (SOFT)
    public void softDeleteLoginHistory(UUID id){
        Optional<LoginHistory> loginHistory = loginHistoryRepository.findById(id);
        if(loginHistory.isPresent()){
            LoginHistory loginHistoryForDle = loginHistory.get();
            loginHistoryForDle.setStatus(false);

            loginHistoryRepository.save(loginHistoryForDle);
        }else{
            throw new Error("LoginHistory Not Found With Id "+id);
        }
    }

    // DELETE LOGIN HISTORIES (HARD)
    public void permanentDeleteLoginHistory(UUID id){
        Optional<LoginHistory> loginHistory = loginHistoryRepository.findById(id);
        if(loginHistory.isPresent()){
            loginHistoryRepository.deleteById(id);
        }else{
            throw new Error("LoginHistory Not Found With ID "+id);
        }
    }
}
