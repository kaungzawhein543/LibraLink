package com.elibrary.LibraLink.controllers;


import com.elibrary.LibraLink.dtos.ErrorLogsDTO;
import com.elibrary.LibraLink.entities.ErrorLogs;
import com.elibrary.LibraLink.services.ErrorLogsService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/exceptions")
public class ErrorLogsController {

    // CONSTANTS VALUES
    private final ErrorLogsService errorLogsService;
    private final ModelMapper modelMapper;

    // CONSTRUCTORS
    public ErrorLogsController(ErrorLogsService errorLogsService, ModelMapper modelMapper) {
        this.errorLogsService = errorLogsService;
        this.modelMapper = modelMapper;
    }


    // GET ALL ERROR LOGS
    @GetMapping("/getAll")
    public ResponseEntity<List<ErrorLogsDTO>> getAllErrorLogs(){
        List<ErrorLogsDTO> errorLogsDTOS = errorLogsService.findAllErrorLogs()
                .stream()
                .map(errorLog -> modelMapper.map(errorLog,ErrorLogsDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(errorLogsDTOS);
    }   

    // UPDATE ERROR LOG STATUS OR ADDITIONAL INFO
    @PutMapping("/update")
    public ResponseEntity<ErrorLogs> updateStatusOrInfo(@RequestBody ErrorLogs errorLogs){
        return new ResponseEntity<>(errorLogsService.updateErrorLogs(errorLogs), HttpStatusCode.valueOf(200));
    }

    // REMOVE ERROR WITH SOFT DELETE
    @PutMapping("/remove/{id}")
    public ResponseEntity<String> removeErrorLogs(@PathVariable Integer id){
        errorLogsService.softDeleteErrorLogs(id);
        return ResponseEntity.ok("Remove Error Log Successfully");
    }
}
