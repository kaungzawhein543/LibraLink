package com.elibrary.LibraLink.repositories;

import com.elibrary.LibraLink.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    // FIND BY USERNAME
    Optional<User> findByUsername(String username);

    // FIND BY EMAIL
    Optional<User> findByEmail(String email);


}
