package com.elibrary.LibraLink.controllers;


import com.elibrary.LibraLink.dtos.EmailServiceDTO;
import com.elibrary.LibraLink.entities.Category;
import com.elibrary.LibraLink.entities.EmailService;
import com.elibrary.LibraLink.services.EmailService_Service;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/emailService")
public class EmailServiceController {

    private final EmailService_Service emailServiceService;
    private final ModelMapper modelMapper;

    public EmailServiceController(EmailService_Service emailServiceService, ModelMapper modelMapper){
        this.emailServiceService = emailServiceService;
        this.modelMapper = modelMapper;
    }

    //ADD Email Service
    @PostMapping("add")
    public ResponseEntity<EmailService> addEmailService(@RequestBody EmailService emailService) {
        return new ResponseEntity<>(emailServiceService.addEmailService(emailService), HttpStatusCode.valueOf(200));
    }

    //GET Email Service BY ID
    @GetMapping("get/{id}")
    public ResponseEntity<EmailServiceDTO> getEmailServiceById(@PathVariable Integer id) {
        return emailServiceService.findEmailServiceById(id)
                .map(emailService -> modelMapper.map(emailService, EmailServiceDTO.class))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    //GET ALL EMAIL SERVICES
    @GetMapping("getAll")
    public ResponseEntity<List<EmailServiceDTO>> getAllEmailServices() {
        List<EmailServiceDTO> emailServiceDTOS = emailServiceService.findAllEmailServices()
                .stream()
                .map(emailService -> modelMapper.map(emailService, EmailServiceDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(emailServiceDTOS);
    }

    //UPDATE EMAIL SERVICES
    @PutMapping("update")
    public ResponseEntity<EmailService> updateEmailService(@RequestBody EmailService emailService) {
        return new ResponseEntity<>(emailServiceService.updateEmailService(emailService),HttpStatusCode.valueOf(200));
    }

    //DELETE EMAIL SERVICE(SOFT)
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteEmailService(@PathVariable Integer id){
        emailServiceService.softDeleteEmailService(id);
        return ResponseEntity.ok("Delete Email Service Successfully.");
    }
}
