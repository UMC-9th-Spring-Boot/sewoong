package com.example.umc.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.umc.domain.review.entity.Review;
import com.example.umc.domain.review.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;

    @Override
    public Page<Review> getMyReviews(Long userId, Integer page) {
        PageRequest pageRequest = PageRequest.of(page - 1, 10);
        return reviewRepository.findByUserUserId(userId, pageRequest);
    }
}
