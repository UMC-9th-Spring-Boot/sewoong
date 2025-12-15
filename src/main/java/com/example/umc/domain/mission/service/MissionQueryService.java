package com.example.umc.domain.mission.service;

import org.springframework.data.domain.Page;

import com.example.umc.domain.mission.entity.Mission;
import com.example.umc.domain.mission.entity.UserMission;
import com.example.umc.domain.mission.enums.UserMissionStatus;

public interface MissionQueryService {
    // 미션 존재 여부 확인
    boolean existsById(Long missionId);

    Page<Mission> getStoreMissions(Long storeId, Integer page);

    Page<UserMission> getUserMissionsByStatus(Long userId, UserMissionStatus status, Integer page);
}