package com.example.umc.global.apiPayload.dto;

import lombok.Builder;

@Builder
public record PagingInfoDTO(
    Integer currentPage,
    Integer totalPages,
    Long totalElements,
    Boolean isFirst,
    Boolean isLast) {
}
