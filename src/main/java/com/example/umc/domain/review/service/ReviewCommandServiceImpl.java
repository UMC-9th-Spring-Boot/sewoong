package com.example.umc.domain.review.service;

import com.example.umc.domain.review.converter.ReviewConverter;
import com.example.umc.domain.review.dto.ReviewReqDTO;
import com.example.umc.domain.review.dto.ReviewResDTO;
import com.example.umc.domain.review.entity.Review;
import com.example.umc.domain.review.repository.ReviewRepository;
import com.example.umc.domain.store.entity.Store;
import com.example.umc.domain.store.exception.StoreException;
import com.example.umc.domain.store.exception.code.StoreErrorCode;
import com.example.umc.domain.store.repository.StoreRepository;
import com.example.umc.domain.user.entity.User;
import com.example.umc.domain.user.exception.UserException;
import com.example.umc.domain.user.exception.code.UserErrorCode;
import com.example.umc.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public ReviewResDTO.CreateReviewDTO createReview(ReviewReqDTO.CreateReviewDTO dto) {
        // 하드코딩된 사용자 조회 (user_id = 1)
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        // 가게 존재 여부 검증
        Store store = storeRepository.findById(dto.storeId())
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        // 리뷰 엔티티 생성
        Review review = ReviewConverter.toReview(dto, user, store);

        // DB 저장
        reviewRepository.save(review);

        // 응답 DTO 생성
        return ReviewConverter.toCreateReviewDTO(review);
    }
}
