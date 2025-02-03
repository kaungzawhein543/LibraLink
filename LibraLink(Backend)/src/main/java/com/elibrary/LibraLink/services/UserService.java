package com.elibrary.LibraLink.services;

import com.elibrary.LibraLink.entities.User;
import com.elibrary.LibraLink.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
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
}
