package com.example.umc.domain.store.controller;

import com.example.umc.domain.store.dto.StoreReqDTO;
import com.example.umc.domain.store.dto.StoreResDTO;
import com.example.umc.domain.store.exception.code.StoreSuccessCode;
import com.example.umc.domain.store.service.StoreCommandService;
import com.example.umc.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stores")
@RequiredArgsConstructor
@Tag(name = "가게", description = "가게 관련 API")
public class StoreController {

    private final StoreCommandService storeCommandService;

    // 가게 추가
    @PostMapping("")
    @Operation(summary = "가게 추가", description = "특정 지역에 가게를 추가합니다.")
    public ApiResponse<StoreResDTO.CreateStoreDTO> createStore(
            @RequestBody @Valid StoreReqDTO.CreateStoreDTO dto
    ) {
        StoreResDTO.CreateStoreDTO response = storeCommandService.createStore(dto);
        return ApiResponse.onSuccess(StoreSuccessCode.STORE_CREATED, response);
    }
}
