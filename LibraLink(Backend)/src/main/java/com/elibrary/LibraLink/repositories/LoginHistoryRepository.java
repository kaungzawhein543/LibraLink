package com.elibrary.LibraLink.repositories;

import com.elibrary.LibraLink.entities.LoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginHistoryRepository extends JpaRepository<LoginHistory,Integer> {
}
