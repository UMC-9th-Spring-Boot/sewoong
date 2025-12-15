package com.example.umc.domain.review.service;

import org.springframework.data.domain.Page;

import com.example.umc.domain.review.entity.Review;

public interface ReviewQueryService {
    Page<Review> getMyReviews(Long userId, Integer page);
}
