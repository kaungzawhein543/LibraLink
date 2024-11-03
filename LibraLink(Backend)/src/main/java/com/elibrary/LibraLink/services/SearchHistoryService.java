package com.elibrary.LibraLink.services;

import com.elibrary.LibraLink.repositories.SearchHistoryRepository;
import org.springframework.stereotype.Service;

@Service
public class SearchHistoryService {

    private final SearchHistoryRepository searchHistoryRepository;

    public SearchHistoryService(SearchHistoryRepository searchHistoryRepository){
        this.searchHistoryRepository = searchHistoryRepository;
    }
}
