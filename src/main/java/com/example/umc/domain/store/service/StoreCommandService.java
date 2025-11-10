package com.example.umc.domain.store.service;

import com.example.umc.domain.store.dto.StoreReqDTO;
import com.example.umc.domain.store.dto.StoreResDTO;

public interface StoreCommandService {
    // 가게 추가
    StoreResDTO.CreateStoreDTO createStore(StoreReqDTO.CreateStoreDTO dto);
}
