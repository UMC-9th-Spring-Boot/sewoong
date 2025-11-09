package com.example.umc.domain.mission.controller;

import com.example.umc.domain.mission.dto.MissionReqDTO;
import com.example.umc.domain.mission.dto.MissionResDTO;
import com.example.umc.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc.domain.mission.service.MissionCommandService;
import com.example.umc.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/missions")
@RequiredArgsConstructor
@Tag(name = "미션", description = "미션 관련 API")
public class MissionController {

    private final MissionCommandService missionCommandService;

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
}
