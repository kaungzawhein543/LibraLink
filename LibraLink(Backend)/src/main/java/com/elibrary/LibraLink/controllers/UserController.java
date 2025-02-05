package com.elibrary.LibraLink.controllers;

import com.elibrary.LibraLink.dtos.LoginRequest;
import com.elibrary.LibraLink.dtos.UserDTO;
import com.elibrary.LibraLink.entities.User;
import com.elibrary.LibraLink.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/auth")
@Slf4j
public class UserController {

    // CONSTANT VALUES
    private final UserService userService;
    private final ModelMapper modelMapper;

    // CONSTRUCTOR
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    // LOGIN METHOD
    @PostMapping("login/{device_id}")
    public ResponseEntity<String> login(@RequestParam("/device_id") UUID device_id, @RequestBody LoginRequest loginRequest, HttpServletResponse response) throws Exception {
        Optional<User> user = userService.findByEmail(loginRequest.getEmail());
        if (user.isEmpty()) {
            throw new Exception("User Not Found With This Email" + loginRequest.getEmail());
        }else if(!user.get().isStatus()){
            throw new Exception("This Account Is Not Available");
        }else {
            String result = userService.checkPasswordAndStoreCookie(loginRequest,user.get(), device_id);
            if (result != null) {
                return ResponseEntity.ok("Login Successfully! : " + result);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
            }
        }
    }

    // REGISTER METHOD
    @PostMapping("/register")
    public ResponseEntity<String> Register(@RequestBody UserDTO userDataFromRequest) throws Exception {
        Optional<User> userDataFromDb = userService.findByEmail(userDataFromRequest.getEmail());
        if(userDataFromDb.isPresent()) {
            throw new Exception("User With That Email is Already Exist"+ userDataFromRequest.getEmail());
        } else {
            User user = modelMapper.map(userDataFromRequest, User.class);
            User result = userService.addUser(user);
            if (result != null ) {
                return ResponseEntity.ok("Register Successfully!");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please Enter Strong Password");
            }
        }
    }
}
