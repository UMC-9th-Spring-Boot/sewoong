package com.example.umc.domain.store.service;

import com.example.umc.domain.store.converter.StoreConverter;
import com.example.umc.domain.store.dto.StoreReqDTO;
import com.example.umc.domain.store.dto.StoreResDTO;
import com.example.umc.domain.store.entity.Store;
import com.example.umc.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public StoreResDTO.CreateStoreDTO createStore(StoreReqDTO.CreateStoreDTO dto) {
        // 가게 엔티티 생성
        Store store = StoreConverter.toStore(dto);

        // DB 저장
        storeRepository.save(store);

        log.info("가게 생성 완료: {} (지역: {})", store.getStoreName(), dto.regionName());

        // 응답 DTO 생성
        return StoreConverter.toCreateStoreDTO(store);
    }
}
