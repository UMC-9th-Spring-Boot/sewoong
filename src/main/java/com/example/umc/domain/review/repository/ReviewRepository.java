package com.example.umc.domain.review.repository;

import com.example.umc.domain.review.entity.Review;
import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

  // 1. 리뷰 작성 - 서비스 계층에서 Spring Data JPA의 save() 사용 예정
  // Review review = Review.builder()
  // .user(user)
  // .store(store)
  // .reviewText(reviewText)
  // .score(score)
  // .build();
  // reviewRepository.save(review);

  // 2. 특정 사용자의 특정 가게 리뷰 조회
  @Query("SELECT r FROM Review r WHERE r.user.userId = :userId AND r.store.storeId = :storeId")
  Review findByUserIdAndStoreId(@Param("userId") Long userId, @Param("storeId") Long storeId);

  // 3. 특정 가게의 모든 리뷰 조회
  @Query("SELECT r FROM Review r WHERE r.store.storeId = :storeId")
  List<Review> findByStoreId(@Param("storeId") Long storeId);

  // 4. 내가 작성한 리뷰 조회 (동적 쿼리) - QueryDSL
  List<Review> searchMyReviews(Predicate predicate);
}
