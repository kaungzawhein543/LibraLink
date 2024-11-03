package com.elibrary.LibraLink.repositories;

import com.elibrary.LibraLink.entities.SearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchHistoryRepository extends JpaRepository<SearchHistory,Integer> {
}
