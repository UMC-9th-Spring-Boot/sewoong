package com.example.umc.domain.mission.service;

public interface MissionQueryService {
    // 미션 존재 여부 확인
    boolean existsById(Long missionId);
}