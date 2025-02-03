package com.elibrary.LibraLink.services;

import com.elibrary.LibraLink.dtos.LoginRequest;
import com.elibrary.LibraLink.entities.User;
import com.elibrary.LibraLink.repositories.UserRepository;
import com.elibrary.LibraLink.security.CookieConfig;
import com.elibrary.LibraLink.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final CookieConfig cookieConfig;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, CookieConfig cookieConfig){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.cookieConfig = cookieConfig;
    }

    //CREATE USER
    public User addUser(User user){
        return userRepository.save(user);
    }

    //GET USER BY ID
    public Optional<User> findUserById(Integer id){
        return userRepository.findById(id);
    }

    // GET USER BY EMAIL
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    //GET ALL USERS
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    //UPDATE USER BY ID
    public User updateUser(User user){
        Optional<User> originalUser = userRepository.findById(user.getId());

        if(originalUser.isPresent()){
            User userForUpdate = originalUser.get();
            userForUpdate.setName(user.getName());
            userForUpdate.setEmail(user.getEmail());
            userForUpdate.setProfile_photo_path(user.getProfile_photo_path());
            userForUpdate.setTheme(user.getTheme());

            return userRepository.save(userForUpdate);
        }else {
            throw new Error("User Not Found With Id "+user.getId());
        }
    }

    //DELETE USER (SOFT)
    public void softDeleteUser(Integer id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            User userForDle = user.get();
            userForDle.setStatus(false);

            userRepository.save(userForDle);
        }else{
            throw new Error("User Not Found With Id "+id);
        }
    }

    //DELETE USER (HARD)
    public void permanentDeleteUser(Integer id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            userRepository.deleteById(id);
        }else{
            throw new Error("User Not Found With Id "+id);
        }
    }

    // CHECK PASSWORD
    public void checkPasswordAndStoreCookie(LoginRequest loginRequest,User user) {


        if(passwordEncoder.matches(loginRequest.getPassword(),user.getPassword())) {

            // GENERATE ACCESS TOKEN
            String accessToken  = jwtUtil.generate_jwt_token(user, "access");

            // GENERATE REFRESH TOKEN
            String refreshToken = jwtUtil.generate_jwt_token(user, "refresh");


            cookieConfig.addTokenToCookie(accessToken, loginRequest.isRememberMe());
        }
    }
}
