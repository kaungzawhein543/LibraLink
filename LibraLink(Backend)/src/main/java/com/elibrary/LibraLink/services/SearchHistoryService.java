package com.elibrary.LibraLink.services;

import com.elibrary.LibraLink.entities.Category;
import com.elibrary.LibraLink.entities.SearchHistory;
import com.elibrary.LibraLink.repositories.SearchHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SearchHistoryService {

    // CONSTANT VALUES
    private final SearchHistoryRepository searchHistoryRepository;

    // CONSTRUCTOR
    public SearchHistoryService(SearchHistoryRepository searchHistoryRepository){
        this.searchHistoryRepository = searchHistoryRepository;
    }

    // CREATE SEARCH HISTORY
    public SearchHistory addSearchHistory(SearchHistory searchHistory){
        return searchHistoryRepository.save(searchHistory);
    }

    // GET SEARCH HISTORY BY ID
    public Optional<SearchHistory> findSearchHistoryById(Integer id){
        return searchHistoryRepository.findById(id);
    }

    // GET ALL SEARCH HISTORIES
    public List<SearchHistory> findAllSearchHistories(){
        return searchHistoryRepository.findAll();
    }


    // DELETE SEARCH HISTORIES (SOFT)
    public void softDeleteSearchHistory(Integer id){
        Optional<SearchHistory> searchHistory = searchHistoryRepository.findById(id);
        if(searchHistory.isPresent()){
            SearchHistory searchHistoryForDle = searchHistory.get();
            searchHistoryForDle.setStatus(false);

            searchHistoryRepository.save(searchHistoryForDle);
        }else{
            throw new Error("SearchHistory Not Found With Id "+id);
        }
    }

    //DELETE SEARCH HISTORY (HARD)
    public void permanentDeleteSearchHistory(Integer id){
        Optional<SearchHistory> searchHistory = searchHistoryRepository.findById(id);
        if(searchHistory.isPresent()){
            searchHistoryRepository.deleteById(id);
        }else{
            throw new Error("SearchHistory Not Found With Id "+id);
        }
    }
}
