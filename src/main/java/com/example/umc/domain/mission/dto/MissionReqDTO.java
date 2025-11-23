package com.example.umc.domain.mission.dto;

import com.example.umc.domain.mission.enums.Region;
import com.example.umc.domain.mission.validation.ExistMissions;
import com.example.umc.domain.store.validation.ExistStores;

public class MissionReqDTO {
        public record ChallengeMissionDTO(
                        @ExistMissions Long missionId) {
        }

        public record CreateMissionDTO(
                        @ExistStores Long storeId,
                        Region region,
                        Long missionMoney,
                        Long missionPoint) {
        }

        public record CompleteMissionDTO(
                        Long challengeMissionId) {
        }
}
