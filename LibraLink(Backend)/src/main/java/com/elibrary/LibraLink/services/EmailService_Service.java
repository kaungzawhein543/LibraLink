package com.elibrary.LibraLink.services;

import com.elibrary.LibraLink.entities.Category;
import com.elibrary.LibraLink.entities.EmailService;
import com.elibrary.LibraLink.repositories.EmailServiceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmailService_Service {

    // CONSTANT VALUES
    private final EmailServiceRepository emailServiceRepository;

    // CONSTRUCTOR
    public EmailService_Service(EmailServiceRepository emailServiceRepository){
        this.emailServiceRepository = emailServiceRepository;
    }

    // CREATE EMAIL SERVICE
    public EmailService addEmailService(EmailService emailService){
        return emailServiceRepository.save(emailService);
    }

    // GET EMAIL SERVICE BY ID
    public Optional<EmailService> findEmailServiceById(Integer id){
        return emailServiceRepository.findById(id);
    }

    // GET ALL EMAIL SERVICES
    public List<EmailService> findAllEmailServices(){
        return emailServiceRepository.findAll();
    }

    // UPDATE EMAIL SERVICES BY ID
    public EmailService updateEmailService(EmailService emailService){
        Optional<EmailService> originalEmailService = emailServiceRepository.findById(emailService.getId());

        if(originalEmailService.isPresent()){
            EmailService emailServiceForUpdate = originalEmailService.get();
            emailServiceForUpdate.setService_name(emailService.getService_name());
            emailServiceForUpdate.setUpdated_at(LocalDateTime.now());
            emailServiceForUpdate.setPort(emailService.getPort());
            emailServiceForUpdate.setPassword(emailService.getPassword());
            emailServiceForUpdate.setApi_key(emailService.getApi_key());
            emailServiceForUpdate.setCurrent_use(emailService.isCurrent_use());
            emailServiceForUpdate.setSmtp_server(emailService.getSmtp_server());
            emailServiceForUpdate.setUsername(emailService.getUsername());

            return emailServiceRepository.save(emailServiceForUpdate);
        }else {
            throw new Error("EmailService Not Found With Id "+emailService.getId());
        }
    }

    // DELETE EMAIL SERVICE (SOFT)
    public void softDeleteEmailService(Integer id){
        Optional<EmailService> emailService = emailServiceRepository.findById(id);
        if(emailService.isPresent()){
            EmailService emailServiceForDle = emailService.get();
            emailServiceForDle.setStatus(false);

            emailServiceRepository.save(emailServiceForDle);
        }else{
            throw new Error("EmailService Not Found With Id "+id);
        }
    }

    //Delete EmailService (hard)
    public void permanentDeleteEmailService(Integer id){
        Optional<EmailService> emailService = emailServiceRepository.findById(id);
        if(emailService.isPresent()){
            emailServiceRepository.deleteById(id);
        }else{
            throw new Error("EmailService Not Found With Id "+id);
        }
    }
}
