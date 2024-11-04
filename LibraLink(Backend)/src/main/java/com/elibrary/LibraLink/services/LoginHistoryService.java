package com.elibrary.LibraLink.services;

import com.elibrary.LibraLink.entities.LoginHistory;
import com.elibrary.LibraLink.repositories.LoginHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoginHistoryService {

    private final LoginHistoryRepository loginHistoryRepository;

    public LoginHistoryService(LoginHistoryRepository loginHistoryRepository){
        this.loginHistoryRepository = loginHistoryRepository;
    }

    //Create LoginHistory
    public LoginHistory addLoginHistory(LoginHistory loginHistory){
        return loginHistoryRepository.save(loginHistory);
    }

    //Get LoginHistory By ID
    public Optional<LoginHistory> findLoginHistoryById(UUID id){
        return loginHistoryRepository.findById(id);
    }

    //Get All LoginHistories
    public List<LoginHistory> findAllLoginHistories(){
        return loginHistoryRepository.findAll();
    }


    //Delete LoginHistory (soft)
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

    //Delete LoginHistory (hard)
    public void permanentDeleteLoginHistory(UUID id){
        Optional<LoginHistory> loginHistory = loginHistoryRepository.findById(id);
        if(loginHistory.isPresent()){
            loginHistoryRepository.deleteById(id);
        }else{
            throw new Error("LoginHistory Not Found With ID "+id);
        }
    }
}
