package com.example.umc.domain.review.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {
    @Builder
    public record CreateReviewDTO(
            Long reviewId,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record ReviewPreViewDTO(
            Long reviewId,
            String storeName,
            BigDecimal score,
            String reviewText,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record ReviewPreViewListDTO(
            List<ReviewPreViewDTO> reviewList,
            Integer listSize,
            Integer currentPage,
            Integer totalPages,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}
}
