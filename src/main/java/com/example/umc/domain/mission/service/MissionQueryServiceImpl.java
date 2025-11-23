package com.example.umc.domain.mission.service;

import com.example.umc.domain.mission.entity.Mission;
import com.example.umc.domain.mission.entity.UserMission;
import com.example.umc.domain.mission.enums.UserMissionStatus;
import com.example.umc.domain.mission.repository.MissionRepository;
import com.example.umc.domain.mission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;

    @Override
    public boolean existsById(Long missionId) {
        return missionRepository.existsById(missionId);
    }

    @Override
    public Page<Mission> getStoreMissions(Long storeId, Integer page) {
        PageRequest pageRequest = PageRequest.of(page - 1, 10);
        return missionRepository.findByStoreStoreId(storeId, pageRequest);
    }

    @Override
    public Page<UserMission> getMyOngoingMissions(Long userId, Integer page) {
        PageRequest pageRequest = PageRequest.of(page - 1, 10);
        return userMissionRepository.findUserMissionsByStatus(userId, UserMissionStatus.IN_PROGRESS, pageRequest);
    }
}