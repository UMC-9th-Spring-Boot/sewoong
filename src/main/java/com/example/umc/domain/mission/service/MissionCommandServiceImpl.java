package com.example.umc.domain.mission.service;

import com.example.umc.domain.mission.converter.MissionConverter;
import com.example.umc.domain.mission.dto.MissionReqDTO;
import com.example.umc.domain.mission.dto.MissionResDTO;
import com.example.umc.domain.mission.entity.Mission;
import com.example.umc.domain.mission.entity.UserMission;
import com.example.umc.domain.mission.exception.MissionException;
import com.example.umc.domain.mission.exception.code.MissionErrorCode;
import com.example.umc.domain.mission.repository.MissionRepository;
import com.example.umc.domain.mission.repository.UserMissionRepository;
import com.example.umc.domain.store.entity.Store;
import com.example.umc.domain.store.exception.StoreException;
import com.example.umc.domain.store.exception.code.StoreErrorCode;
import com.example.umc.domain.store.repository.StoreRepository;
import com.example.umc.domain.user.entity.User;
import com.example.umc.domain.user.exception.UserException;
import com.example.umc.domain.user.exception.code.UserErrorCode;
import com.example.umc.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public MissionResDTO.ChallengeMissionDTO challengeMission(MissionReqDTO.ChallengeMissionDTO dto) {
        // 하드코딩된 사용자 조회 (user_id = 1)
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        // 미션 존재 여부 검증
        Mission mission = missionRepository.findById(dto.missionId())
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        // 가게 정보는 미션에서 가져옴
        Store store = mission.getStore();

        // 이미 도전 중인 미션인지 확인 (선택적 - 필요시 구현)
        // boolean alreadyChallenged =
        // userMissionRepository.existsByUserAndMission(user, mission);
        // if (alreadyChallenged) {
        // throw new MissionException(MissionErrorCode.MISSION_ALREADY_CHALLENGED);
        // }

        // UserMission 엔티티 생성
        UserMission userMission = MissionConverter.toUserMission(mission, user, store);

        // DB 저장
        userMissionRepository.save(userMission);

        // 응답 DTO 생성
        return MissionConverter.toChallengeMissionDTO(userMission);
    }

    @Override
    @Transactional
    public MissionResDTO.CreateMissionDTO createMission(MissionReqDTO.CreateMissionDTO dto) {
        // 가게 존재 여부 검증
        Store store = storeRepository.findById(dto.storeId())
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        // Mission 엔티티 생성
        Mission mission = MissionConverter.toMission(dto, store);

        // DB 저장
        missionRepository.save(mission);

        // 응답 DTO 생성
        return MissionConverter.toCreateMissionDTO(mission);
    }
}
