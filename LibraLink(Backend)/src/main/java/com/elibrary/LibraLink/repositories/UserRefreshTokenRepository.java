package com.elibrary.LibraLink.repositories;

import com.elibrary.LibraLink.entities.UserRefreshTokens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshTokens, Integer> {
}
