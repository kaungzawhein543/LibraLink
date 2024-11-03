package com.elibrary.LibraLink.services;

import com.elibrary.LibraLink.entities.Category;
import com.elibrary.LibraLink.entities.ErrorLogs;
import com.elibrary.LibraLink.repositories.ErrorLogsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ErrorLogsService {

    private final ErrorLogsRepository errorLogsRepository;

    public ErrorLogsService(ErrorLogsRepository errorLogsRepository){
        this.errorLogsRepository = errorLogsRepository;
    }

    //Create ErrorLogs
    public ErrorLogs addErrorLogs(ErrorLogs errorLogs){
        return errorLogsRepository.save(errorLogs);
    }

    //Get ErrorLogs By ID
    public Optional<ErrorLogs> findErrorLogsById(Integer id){
        return errorLogsRepository.findById(id);
    }

    //Get All ErrorLogs
    public List<ErrorLogs> findAllErrorLogs(){
        return errorLogsRepository.findAll();
    }

    //Update ErrorLogs By id
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

    //Delete ErrorLogs (soft)
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

    //Delete errorLogs (hard)
    public void permanentDeleteErrorLogs(Integer id){
        Optional<ErrorLogs> errorLogs = errorLogsRepository.findById(id);
        if(errorLogs.isPresent()){
            errorLogsRepository.deleteById(id);
        }else{
            throw new Error("ErrorLogs Not Found With Id "+id);
        }
    }
}
