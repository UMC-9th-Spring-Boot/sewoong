package com.example.umc.domain.review.service;

import com.example.umc.domain.review.entity.QReview;
import com.example.umc.domain.review.entity.Review;
import com.example.umc.domain.review.repository.ReviewRepository;
import com.example.umc.domain.store.entity.QStore;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

  private final ReviewRepository reviewRepository;

  public List<Review> searchMyReviews(Long userId, String storeName, String starRange) {
    QReview review = QReview.review;
    BooleanBuilder builder = new BooleanBuilder();

    // 필수 조건: 특정 사용자의 리뷰만 조회 (로그인 로직이 없기 때문)
    builder.and(review.user.userId.eq(userId));

    // 동적 쿼리: 가게명 필터링 (선택)
    if (storeName != null && !storeName.trim().isEmpty()) {
      builder.and(review.store.storeName.contains(storeName));
    }

    // 동적 쿼리: 별점 범위 필터링 (선택)
    if (starRange != null && !starRange.trim().isEmpty()) {
      switch (starRange) {
        case "5점":
          builder.and(review.score.goe(new BigDecimal("4.5")));
          break;
        case "4점대":
          builder.and(review.score.goe(new BigDecimal("4.0"))
              .and(review.score.lt(new BigDecimal("5.0"))));
          break;
        case "3점대":
          builder.and(review.score.goe(new BigDecimal("3.0"))
              .and(review.score.lt(new BigDecimal("4.0"))));
          break;
        case "2점대":
          builder.and(review.score.goe(new BigDecimal("2.0"))
              .and(review.score.lt(new BigDecimal("3.0"))));
          break;
        case "1점대":
          builder.and(review.score.goe(new BigDecimal("1.0"))
              .and(review.score.lt(new BigDecimal("2.0"))));
          break;
      }
    }

    return reviewRepository.searchMyReviews(builder);
  }
}
