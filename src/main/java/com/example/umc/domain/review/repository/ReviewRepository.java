package com.example.umc.domain.review.repository;

import com.example.umc.domain.review.entity.Review;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

  @Query("SELECT r FROM Review r WHERE r.user.userId = :userId AND r.store.storeId = :storeId")
  Review findByUserIdAndStoreId(@Param("userId") Long userId, @Param("storeId") Long storeId);

  @Query("SELECT r FROM Review r WHERE r.store.storeId = :storeId")
  List<Review> findByStoreId(@Param("storeId") Long storeId);

  List<Review> searchMyReviews(Predicate predicate);

  @Query("SELECT r FROM Review r " +
      "WHERE r.user.userId = :userId " +
      "ORDER BY r.reviewId DESC")
  Page<Review> findByUserUserId(
      @Param("userId") Long userId,
      Pageable pageable
  );
}
