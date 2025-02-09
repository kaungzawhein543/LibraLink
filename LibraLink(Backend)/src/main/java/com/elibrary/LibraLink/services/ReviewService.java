package com.elibrary.LibraLink.services;


import com.elibrary.LibraLink.entities.Review;
import com.elibrary.LibraLink.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    // CONSTANT VALUES
    private final ReviewRepository reviewRepository;

    // CONSTRUCTOR
    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    //Create Review
    public Review addReview(Review review){
        return reviewRepository.save(review);
    }

    //Get Review By ID
    public Optional<Review> findReviewById(Integer id){
        return reviewRepository.findById(id);
    }

    //Get All Reviews
    public List<Review> findAllReviews(){
        return reviewRepository.findAll();
    }

    //Update Review By id
    public Review updateReview(Review review){
        Optional<Review> originalReview = reviewRepository.findById(review.getId());

        if(originalReview.isPresent()){
            Review reviewForUpdate = originalReview.get();
            reviewForUpdate.setReview_content(review.getReview_content());
            reviewForUpdate.setRate(review.getRate());
            reviewForUpdate.setUpdated_at(LocalDateTime.now());
            return reviewRepository.save(reviewForUpdate);
        }else {
            throw new Error("Review Not Found With Id "+review.getId());
        }
    }

    //Delete Review (soft)
    public void softDeleteReview(Integer id){
        Optional<Review> review = reviewRepository.findById(id);
        if(review.isPresent()){
            Review reviewForDle = review.get();
            reviewForDle.setStatus(false);

            reviewRepository.save(reviewForDle);
        }else{
            throw new Error("Review Not Found With Id "+id);
        }
    }

}
