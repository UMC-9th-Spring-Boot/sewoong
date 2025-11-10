package com.example.umc.domain.review.converter;

import com.example.umc.domain.review.dto.ReviewReqDTO;
import com.example.umc.domain.review.dto.ReviewResDTO;
import com.example.umc.domain.review.entity.Review;
import com.example.umc.domain.store.entity.Store;
import com.example.umc.domain.user.entity.User;

public class ReviewConverter {

    // Entity -> DTO
    public static ReviewResDTO.CreateReviewDTO toCreateReviewDTO(Review review) {
        return ReviewResDTO.CreateReviewDTO.builder()
                .reviewId(review.getReviewId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    // DTO -> Entity
    public static Review toReview(ReviewReqDTO.CreateReviewDTO dto, User user, Store store) {
        return Review.builder()
                .user(user)
                .store(store)
                .reviewText(dto.reviewText())
                .score(dto.score())
                .build();
    }
}
