package com.example.umc.domain.store.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class StoreResDTO {
    @Builder
    public record CreateStoreDTO(
            Long storeId,
            LocalDateTime createdAt
    ) {}
}
