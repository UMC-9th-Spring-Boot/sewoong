package com.example.umc.domain.mission.service;

import com.example.umc.domain.mission.dto.MissionReqDTO;
import com.example.umc.domain.mission.dto.MissionResDTO;

public interface MissionCommandService {
    // 미션 도전하기
    MissionResDTO.ChallengeMissionDTO challengeMission(MissionReqDTO.ChallengeMissionDTO dto);

    // 미션 추가하기
    MissionResDTO.CreateMissionDTO createMission(MissionReqDTO.CreateMissionDTO dto);

    // 진행중인 미션 완료 처리
    MissionResDTO.CompleteMissionDTO completeMission(MissionReqDTO.CompleteMissionDTO dto);
}
