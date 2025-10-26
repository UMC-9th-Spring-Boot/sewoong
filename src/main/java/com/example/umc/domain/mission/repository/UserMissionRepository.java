package com.example.umc.domain.mission.repository;

import com.example.umc.domain.mission.entity.UserMission;
import com.example.umc.domain.mission.enums.UserMissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

  // 1. 진행중/완료 미션 조회 - status를 @Param으로 받아서 처리
  @Query("SELECT um FROM UserMission um " +
      "JOIN FETCH um.mission m " +
      "JOIN FETCH um.store s " +
      "WHERE um.user.userId = :userId AND um.status = :status " +
      "ORDER BY um.challengeMissionId DESC")
  Page<UserMission> findUserMissionsByStatus(@Param("userId") Long userId,
      @Param("status") UserMissionStatus status,
      Pageable pageable);

  // 2. 커서 페이징 - 2번째 페이지 이상 조회 (기본 조회가 아닌 경우)
  @Query("SELECT um FROM UserMission um " +
      "JOIN FETCH um.mission m " +
      "JOIN FETCH um.store s " +
      "WHERE um.user.userId = :userId AND um.status = :status " +
      "AND um.challengeMissionId < :cursor " +
      "ORDER BY um.challengeMissionId DESC")
  List<UserMission> findUserMissionsNextPage(@Param("userId") Long userId,
      @Param("status") UserMissionStatus status,
      @Param("cursor") Long cursor,
      Pageable pageable);
}