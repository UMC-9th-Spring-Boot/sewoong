package com.example.umc.domain.review.converter;

import com.example.umc.domain.review.dto.ReviewReqDTO;
import com.example.umc.domain.review.dto.ReviewResDTO;
import com.example.umc.domain.review.entity.Review;
import com.example.umc.domain.store.entity.Store;
import com.example.umc.domain.user.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

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

    // Entity -> ReviewPreViewDTO
    public static ReviewResDTO.ReviewPreViewDTO toReviewPreViewDTO(Review review) {
        return ReviewResDTO.ReviewPreViewDTO.builder()
                .reviewId(review.getReviewId())
                .storeName(review.getStore().getStoreName())
                .score(review.getScore())
                .reviewText(review.getReviewText())
                .createdAt(review.getCreatedAt())
                .build();
    }

    // Page<Review> -> ReviewPreViewListDTO
    public static ReviewResDTO.ReviewPreViewListDTO toReviewPreViewListDTO(Page<Review> reviewPage) {
        List<ReviewResDTO.ReviewPreViewDTO> reviewList = reviewPage.stream()
                .map(ReviewConverter::toReviewPreViewDTO)
                .toList();

        return ReviewResDTO.ReviewPreViewListDTO.builder()
                .reviewList(reviewList)
                .listSize(reviewList.size())
                .currentPage(reviewPage.getNumber() + 1) // 0-based를 1-based로 변환
                .totalPages(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .isFirst(reviewPage.isFirst())
                .isLast(reviewPage.isLast())
                .build();
    }
}
