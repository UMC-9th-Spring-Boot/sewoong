package com.example.umc.domain.review.dto;

import com.example.umc.domain.store.validation.ExistStores;

import java.math.BigDecimal;

public class ReviewReqDTO {
    public record CreateReviewDTO(
            @ExistStores Long storeId,
            String reviewText,
            BigDecimal score) {
    }
}
