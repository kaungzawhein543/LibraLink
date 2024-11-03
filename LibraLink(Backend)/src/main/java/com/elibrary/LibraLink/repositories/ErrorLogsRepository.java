package com.elibrary.LibraLink.repositories;

import com.elibrary.LibraLink.entities.ErrorLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorLogsRepository extends JpaRepository<ErrorLogs,Integer> {
}
