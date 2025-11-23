package com.example.umc.domain.mission.controller;

import com.example.umc.domain.mission.converter.MissionConverter;
import com.example.umc.domain.mission.dto.MissionReqDTO;
import com.example.umc.domain.mission.dto.MissionResDTO;
import com.example.umc.domain.mission.entity.Mission;
import com.example.umc.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc.domain.mission.service.MissionCommandService;
import com.example.umc.domain.mission.service.MissionQueryService;
import com.example.umc.global.apiPayload.ApiResponse;
import com.example.umc.global.apiPayload.code.status.SuccessStatus;
import com.example.umc.global.validation.CheckPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/missions")
@RequiredArgsConstructor
@Validated
@Tag(name = "미션", description = "미션 관련 API")
public class MissionController {

    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    // 미션 도전하기
    @PostMapping("/challenge")
    @Operation(summary = "미션 도전하기", description = "가게의 미션을 도전 중인 미션에 추가합니다.")
    public ApiResponse<MissionResDTO.ChallengeMissionDTO> challengeMission(
            @RequestBody @Valid MissionReqDTO.ChallengeMissionDTO dto
    ) {
        MissionResDTO.ChallengeMissionDTO response = missionCommandService.challengeMission(dto);
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_CHALLENGED, response);
    }

    // 미션 추가하기
    @PostMapping("")
    @Operation(summary = "미션 추가", description = "가게에 미션을 추가합니다.")
    public ApiResponse<MissionResDTO.CreateMissionDTO> createMission(
            @RequestBody @Valid MissionReqDTO.CreateMissionDTO dto
    ) {
        MissionResDTO.CreateMissionDTO response = missionCommandService.createMission(dto);
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_CREATED, response);
    }

    @GetMapping("/stores/{storeId}")
    @Operation(summary = "특정 가게의 미션 목록 조회",
               description = "특정 가게의 모든 미션을 페이지 기반 페이징하여 조회합니다. 한 페이지에 10개씩 조회됩니다.")
    public ApiResponse<MissionResDTO.MissionPreViewListDTO> getStoreMissionList(
            @Parameter(description = "가게 ID", required = true) @PathVariable Long storeId,
            @Parameter(description = "페이지 번호 (1 이상)", required = true, example = "1")
            @RequestParam(defaultValue = "1") @CheckPage Integer page
    ) {
        Page<Mission> missionPage = missionQueryService.getStoreMissions(storeId, page);
        MissionResDTO.MissionPreViewListDTO response = MissionConverter.toMissionPreViewListDTO(missionPage);
        return ApiResponse.onSuccess(SuccessStatus._OK, response);
    }

    @GetMapping("/my-ongoing")
    @Operation(summary = "내가 진행중인 미션 목록 조회",
               description = "로그인한 사용자가 진행중인 미션을 페이지 기반 페이징하여 조회합니다. 한 페이지에 10개씩 조회됩니다.")
    public ApiResponse<MissionResDTO.UserMissionPreViewListDTO> getMyOngoingMissionList(
            @Parameter(description = "사용자 ID", required = true) @RequestParam Long userId,
            @Parameter(description = "페이지 번호 (1 이상)", required = true, example = "1")
            @RequestParam(defaultValue = "1") @CheckPage Integer page
    ) {
        Page<com.example.umc.domain.mission.entity.UserMission> userMissionPage = missionQueryService.getMyOngoingMissions(userId, page);
        MissionResDTO.UserMissionPreViewListDTO response = MissionConverter.toUserMissionPreViewListDTO(userMissionPage);
        return ApiResponse.onSuccess(SuccessStatus._OK, response);
    }

    // 진행중인 미션 완료 처리
    @PatchMapping("/complete")
    @Operation(summary = "진행중인 미션 완료 처리",
               description = "진행중인 미션을 완료 상태로 변경하고, 변경된 미션 정보를 조회하여 반환합니다.")
    public ApiResponse<MissionResDTO.CompleteMissionDTO> completeMission(
            @RequestBody @Valid MissionReqDTO.CompleteMissionDTO dto
    ) {
        MissionResDTO.CompleteMissionDTO response = missionCommandService.completeMission(dto);
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_COMPLETED, response);
    }
}
