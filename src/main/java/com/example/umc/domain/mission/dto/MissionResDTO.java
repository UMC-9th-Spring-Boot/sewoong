package com.example.umc.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class MissionResDTO {
    @Builder
    public record ChallengeMissionDTO(
            Long challengeMissionId,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record CreateMissionDTO(
            Long missionId,
            LocalDateTime createdAt
    ) {}
}
