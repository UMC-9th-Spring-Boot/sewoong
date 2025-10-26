package com.example.umc.domain.mission.repository;

import com.example.umc.domain.mission.entity.Mission;
import com.example.umc.domain.mission.enums.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
  // 1. 홈 화면 미션 목록 조회 - 사용자가 아직 수행하지 않은 특정 지역 미션 조회
  @Query("SELECT m FROM Mission m " +
      "JOIN FETCH m.store s " +
      "LEFT JOIN UserMission um ON m.missionId = um.mission.missionId AND um.user.userId = :userId " +
      "WHERE m.region = :region " +
      "AND um.mission.missionId IS NULL " +
      "ORDER BY m.missionId DESC")
  Page<Mission> findAvailableMissionsByRegion(@Param("userId") Long userId,
      @Param("region") Region region,
      Pageable pageable);

  // 1-1. 홈 화면 미션 목록 조회 + 완료된 미션 수 포함
  @Query("SELECT m, " +
      "       (SELECT COUNT(um2) FROM UserMission um2 " +
      "        WHERE um2.mission.missionId = m.missionId " +
      "        AND um2.completedAt IS NOT NULL) as completedCount " +
      "FROM Mission m " +
      "JOIN FETCH m.store s " +
      "LEFT JOIN UserMission um ON m.missionId = um.mission.missionId AND um.user.userId = :userId " +
      "WHERE m.region = :region " +
      "AND um.mission.missionId IS NULL " +
      "ORDER BY m.missionId DESC")
  Page<Object[]> findAvailableMissionsWithCompletedCount(@Param("userId") Long userId,
      @Param("region") Region region,
      Pageable pageable);

  // 2. 홈 화면 미션 목록 조회 - 커서 방식 페이징 (다음 페이지)
  @Query("SELECT m FROM Mission m " +
      "JOIN FETCH m.store s " +
      "LEFT JOIN UserMission um ON m.missionId = um.mission.missionId AND um.user.userId = :userId " +
      "WHERE m.region = :region " +
      "AND um.mission.missionId IS NULL " +
      "AND m.missionId < :cursor " +
      "ORDER BY m.missionId DESC")
  List<Mission> findAvailableMissionsNextPage(@Param("userId") Long userId,
      @Param("region") Region region,
      @Param("cursor") Long cursor,
      Pageable pageable);
}