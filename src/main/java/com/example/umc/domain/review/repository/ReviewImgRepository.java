package com.example.umc.domain.review.repository;

import com.example.umc.domain.review.entity.ReviewImg;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewImgRepository extends JpaRepository<ReviewImg, Long> {

  // 1. 리뷰 이미지 저장 - 서비스 계층에서 Spring Data JPA의 save() 사용 예정 
  // ReviewImg reviewImg = ReviewImg.builder()
  // .review(review)
  // .reviewImg(imageUrl)
  // .build();
  // reviewImgRepository.save(reviewImg);

  // 2. 특정 리뷰의 모든 이미지 조회
  @Query("SELECT ri FROM ReviewImg ri WHERE ri.review.reviewId = :reviewId")
  List<ReviewImg> findByReviewId(@Param("reviewId") Long reviewId);
}
