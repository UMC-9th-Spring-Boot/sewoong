package com.example.umc.domain.review.controller;

import com.example.umc.domain.review.dto.ReviewResponseDto;
import com.example.umc.domain.review.service.ReviewService;
import com.example.umc.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
@Tag(name = "리뷰", description = "리뷰 관련 API")
public class ReviewController {

  private final ReviewService reviewService;

  @GetMapping("/my-reviews")
  @Operation(summary = "내가 작성한 리뷰 조회", description = "특정 사용자가 작성한 리뷰를 가게명과 별점 범위로 필터링하여 조회합니다.")
  public ApiResponse<List<ReviewResponseDto>> getMyReviews(
      @Parameter(description = "사용자 ID", required = true) @RequestParam Long userId,

      @Parameter(description = "가게명 (선택사항)", example = "반이학생마라탕마라반") @RequestParam(required = false) String storeName,

      @Parameter(description = "별점 범위 (선택사항)", example = "4점대") @RequestParam(required = false) String starRange) {
    List<ReviewResponseDto> reviews = reviewService.searchMyReviews(userId, storeName, starRange)
        .stream()
        .map(ReviewResponseDto::from)
        .collect(Collectors.toList());
    return ApiResponse.onSuccess(reviews);
  }
}
