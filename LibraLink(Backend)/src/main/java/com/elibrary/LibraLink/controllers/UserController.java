package com.elibrary.LibraLink.controllers;

import com.elibrary.LibraLink.dtos.LoginRequest;
import com.elibrary.LibraLink.dtos.UserDTO;
import com.elibrary.LibraLink.entities.User;
import com.elibrary.LibraLink.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/auth")
@Slf4j
public class UserController {

    // CONSTANT VALUES
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    // CONSTRUCTOR
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    // LOGIN METHOD
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) throws Exception {
        Optional<User> user = userService.findByEmail(loginRequest.getEmail());
        if (user.isEmpty()) {
            throw new Exception("User Not Found With This Email" + loginRequest.getEmail());
        }else if(!user.get().isStatus()){
            throw new Exception("This Account Is Not Available");
        }else {
            String result = userService.checkPasswordAndStoreCookie(loginRequest,user.get());
            if (result != null) {
                return ResponseEntity.ok("Login Successfully!" + result);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
            }
        }
    }

    // REGISTER METHOD
    public ResponseEntity<String> Register(@RequestBody UserDTO userDataFromRequest) throws Exception {
        Optional<User> userDataFromDb = userService.findByEmail(userDataFromRequest.getEmail());
        if(userDataFromDb.isPresent()) {
            throw new Exception("User With That Email is Already Exist"+ userDataFromRequest.getEmail());
        }
    }
}
