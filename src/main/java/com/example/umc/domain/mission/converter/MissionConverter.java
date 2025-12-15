package com.example.umc.domain.mission.converter;

import com.example.umc.domain.mission.dto.MissionReqDTO;
import com.example.umc.domain.mission.dto.MissionResDTO;
import com.example.umc.domain.mission.entity.Mission;
import com.example.umc.domain.mission.entity.UserMission;
import com.example.umc.domain.store.entity.Store;
import com.example.umc.domain.user.entity.User;
import com.example.umc.global.apiPayload.dto.PagingInfoDTO;
import com.example.umc.global.common.util.PageUtil;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

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

        // Entity -> MissionPreViewDTO
        public static MissionResDTO.MissionPreViewDTO toMissionPreViewDTO(Mission mission) {
                return MissionResDTO.MissionPreViewDTO.builder()
                                .missionId(mission.getMissionId())
                                .storeName(mission.getStore().getStoreName())
                                .missionMoney(mission.getMissionMoney())
                                .missionPoint(mission.getMissionPoint())
                                .region(mission.getRegion())
                                .createdAt(mission.getCreatedAt())
                                .build();
        }

        // Page<Mission> -> MissionPreViewListDTO
        public static MissionResDTO.MissionPreViewListDTO toMissionPreViewListDTO(Page<Mission> missionPage) {
                List<MissionResDTO.MissionPreViewDTO> missionList = missionPage.stream()
                                .map(MissionConverter::toMissionPreViewDTO)
                                .toList();

                PagingInfoDTO pagingInfo = PageUtil.toPagingInfo(missionPage);

                return MissionResDTO.MissionPreViewListDTO.builder()
                                .missionList(missionList)
                                .listSize(missionList.size())
                                .currentPage(pagingInfo.currentPage())
                                .totalPages(pagingInfo.totalPages())
                                .totalElements(pagingInfo.totalElements())
                                .isFirst(pagingInfo.isFirst())
                                .isLast(pagingInfo.isLast())
                                .build();
        }

        // UserMission -> UserMissionPreViewDTO
        public static MissionResDTO.UserMissionPreViewDTO toUserMissionPreViewDTO(UserMission userMission) {
                return MissionResDTO.UserMissionPreViewDTO.builder()
                                .challengeMissionId(userMission.getChallengeMissionId())
                                .storeName(userMission.getStore().getStoreName())
                                .missionMoney(userMission.getMission().getMissionMoney())
                                .missionPoint(userMission.getMission().getMissionPoint())
                                .region(userMission.getMission().getRegion())
                                .status(userMission.getStatus())
                                .challengeAt(userMission.getChallengeAt())
                                .createdAt(userMission.getCreatedAt())
                                .build();
        }

        // Page<UserMission> -> UserMissionPreViewListDTO
        public static MissionResDTO.UserMissionPreViewListDTO toUserMissionPreViewListDTO(
                        Page<UserMission> userMissionPage) {
                List<MissionResDTO.UserMissionPreViewDTO> userMissionList = userMissionPage.stream()
                                .map(MissionConverter::toUserMissionPreViewDTO)
                                .toList();

                PagingInfoDTO pagingInfo = PageUtil.toPagingInfo(userMissionPage);

                return MissionResDTO.UserMissionPreViewListDTO.builder()
                                .userMissionList(userMissionList)
                                .listSize(userMissionList.size())
                                .currentPage(pagingInfo.currentPage())
                                .totalPages(pagingInfo.totalPages())
                                .totalElements(pagingInfo.totalElements())
                                .isFirst(pagingInfo.isFirst())
                                .isLast(pagingInfo.isLast())
                                .build();
        }

        // UserMission -> CompleteMissionDTO
        public static MissionResDTO.CompleteMissionDTO toCompleteMissionDTO(UserMission userMission) {
                return MissionResDTO.CompleteMissionDTO.builder()
                                .challengeMissionId(userMission.getChallengeMissionId())
                                .status(userMission.getStatus())
                                .completedAt(userMission.getCompletedAt())
                                .updatedAt(userMission.getUpdatedAt())
                                .build();
        }
}
