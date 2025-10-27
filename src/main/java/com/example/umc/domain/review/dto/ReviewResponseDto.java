package com.example.umc.domain.review.dto;

import com.example.umc.domain.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDto {
  private Long reviewId;
  private String reviewText;
  private BigDecimal score;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  // 가게 정보
  private StoreInfo storeInfo;

  // 사용자 정보
  private UserInfo userInfo;

  @Getter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class StoreInfo {
    private Long storeId;
    private String storeName;
    private String storeAddress;
  }

  @Getter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class UserInfo {
    private Long userId;
    private String name;
  }

  public static ReviewResponseDto from(Review review) {
    return ReviewResponseDto.builder()
        .reviewId(review.getReviewId())
        .reviewText(review.getReviewText())
        .score(review.getScore())
        .createdAt(review.getCreatedAt())
        .updatedAt(review.getUpdatedAt())
        .storeInfo(StoreInfo.builder()
            .storeId(review.getStore().getStoreId())
            .storeName(review.getStore().getStoreName())
            .storeAddress(review.getStore().getStoreAddress())
            .build())
        .userInfo(UserInfo.builder()
            .userId(review.getUser().getUserId())
            .name(review.getUser().getName())
            .build())
        .build();
  }
}
