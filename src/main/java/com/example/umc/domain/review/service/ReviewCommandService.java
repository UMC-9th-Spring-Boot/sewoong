package com.example.umc.domain.review.service;

import com.example.umc.domain.review.dto.ReviewReqDTO;
import com.example.umc.domain.review.dto.ReviewResDTO;

public interface ReviewCommandService {
    // 리뷰 작성
    ReviewResDTO.CreateReviewDTO createReview(ReviewReqDTO.CreateReviewDTO dto);
}
