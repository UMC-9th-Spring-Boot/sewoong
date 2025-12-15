package com.example.umc.domain.mission.dto;

import com.example.umc.domain.mission.enums.Region;
import com.example.umc.domain.mission.enums.UserMissionStatus;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

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

    @Builder
    public record MissionPreViewDTO(
            Long missionId,
            String storeName,
            Long missionMoney,
            Long missionPoint,
            Region region,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record MissionPreViewListDTO(
            List<MissionPreViewDTO> missionList,
            Integer listSize,
            Integer currentPage,
            Integer totalPages,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    @Builder
    public record UserMissionPreViewDTO(
            Long challengeMissionId,
            String storeName,
            Long missionMoney,
            Long missionPoint,
            Region region,
            UserMissionStatus status,
            LocalDateTime challengeAt,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record UserMissionPreViewListDTO(
            List<UserMissionPreViewDTO> userMissionList,
            Integer listSize,
            Integer currentPage,
            Integer totalPages,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    @Builder
    public record CompleteMissionDTO(
            Long challengeMissionId,
            UserMissionStatus status,
            java.time.LocalDateTime completedAt,
            java.time.LocalDateTime updatedAt
    ) {}
}
