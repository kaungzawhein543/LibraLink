package com.elibrary.LibraLink.repositories;

import com.elibrary.LibraLink.entities.EmailService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailServiceRepository extends JpaRepository<EmailService,Integer> {
}
