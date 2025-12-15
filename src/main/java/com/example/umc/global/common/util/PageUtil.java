package com.example.umc.global.common.util;

import com.example.umc.global.apiPayload.dto.PagingInfoDTO;
import org.springframework.data.domain.Page;

public class PageUtil {

  /**
   * Page 객체로부터 1-based 페이징 정보를 생성합니다.
   * Spring Data의 Page는 0-based이지만, API 응답은 1-based로 변환합니다.
   */
  public static <T> PagingInfoDTO toPagingInfo(Page<T> page) {
    return PagingInfoDTO.builder()
        .currentPage(page.getNumber() + 1) // 0-based를 1-based로 변환
        .totalPages(page.getTotalPages())
        .totalElements(page.getTotalElements())
        .isFirst(page.isFirst())
        .isLast(page.isLast())
        .build();
  }
}
