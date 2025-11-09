package com.example.umc.domain.store.converter;

import com.example.umc.domain.store.dto.StoreReqDTO;
import com.example.umc.domain.store.dto.StoreResDTO;
import com.example.umc.domain.store.entity.Store;

public class StoreConverter {

    // Entity -> DTO
    public static StoreResDTO.CreateStoreDTO toCreateStoreDTO(Store store) {
        return StoreResDTO.CreateStoreDTO.builder()
                .storeId(store.getStoreId())
                .createdAt(store.getCreatedAt())
                .build();
    }

    // DTO -> Entity
    public static Store toStore(StoreReqDTO.CreateStoreDTO dto) {
        return Store.builder()
                .storeName(dto.storeName())
                .storeAddress(dto.storeAddress())
                .storeType(dto.storeType())
                .build();
    }
}
