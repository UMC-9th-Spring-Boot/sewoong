package com.example.umc.domain.store.dto;

import com.example.umc.domain.store.enums.StoreType;

public class StoreReqDTO {
    public record CreateStoreDTO(
            String storeName,
            String storeAddress,
            StoreType storeType,
            String regionName  // 지역 이름
    ) {}
}
