package com.example.umc.domain.mission.converter;

import com.example.umc.domain.mission.dto.MissionReqDTO;
import com.example.umc.domain.mission.dto.MissionResDTO;
import com.example.umc.domain.mission.entity.Mission;
import com.example.umc.domain.mission.entity.UserMission;
import com.example.umc.domain.store.entity.Store;
import com.example.umc.domain.user.entity.User;

import java.time.LocalDateTime;

public class MissionConverter {

    // Entity -> DTO (ChallengeMission)
    public static MissionResDTO.ChallengeMissionDTO toChallengeMissionDTO(UserMission userMission) {
        return MissionResDTO.ChallengeMissionDTO.builder()
                .challengeMissionId(userMission.getChallengeMissionId())
                .createdAt(userMission.getCreatedAt())
                .build();
    }

    // DTO -> Entity (ChallengeMission)
    public static UserMission toUserMission(Mission mission, User user, Store store) {
        return UserMission.builder()
                .user(user)
                .mission(mission)
                .store(store)
                .challengeAt(LocalDateTime.now())
                .build();
    }

    // Entity -> DTO (CreateMission)
    public static MissionResDTO.CreateMissionDTO toCreateMissionDTO(Mission mission) {
        return MissionResDTO.CreateMissionDTO.builder()
                .missionId(mission.getMissionId())
                .createdAt(mission.getCreatedAt())
                .build();
    }

    // DTO -> Entity (CreateMission)
    public static Mission toMission(MissionReqDTO.CreateMissionDTO dto, Store store) {
        return Mission.builder()
                .store(store)
                .region(dto.region())
                .missionMoney(dto.missionMoney())
                .missionPoint(dto.missionPoint())
                .build();
    }
}
