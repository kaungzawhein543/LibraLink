package com.elibrary.LibraLink.services;

import com.elibrary.LibraLink.entities.UserRefreshTokens;
import com.elibrary.LibraLink.repositories.UserRefreshTokenRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class UserRefreshTokenService {

    // CONSTANTS VARIABLES
    private final UserRefreshTokenRepository userRefreshTokenRepository;

    // CONSTRUCTOR
    public UserRefreshTokenService(UserRefreshTokenRepository userRefreshTokenRepository) {
        this.userRefreshTokenRepository = userRefreshTokenRepository;
    }

    // SAVE USER REFRESH TOKEN
    public UserRefreshTokens save(UserRefreshTokens userRefreshTokens) {
        return userRefreshTokenRepository.save(userRefreshTokens);
    }

    // FIND BY ID
    public Optional<UserRefreshTokens> findById(Integer id) {
        return userRefreshTokenRepository.findById(id);
    }

    // DELETE BY ID
    public void deleteById(Integer id) {
        userRefreshTokenRepository.deleteById(id);
    }

    // GET BY ID
    public UserRefreshTokens getById(Integer integer) {
        return null;
    }

}
