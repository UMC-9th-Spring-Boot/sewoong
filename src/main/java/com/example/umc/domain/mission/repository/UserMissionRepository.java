package com.example.umc.domain.mission.repository;

import com.example.umc.domain.mission.entity.UserMission;
import com.example.umc.domain.mission.enums.UserMissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

  @Query("SELECT um FROM UserMission um " +
      "JOIN FETCH um.mission m " +
      "JOIN FETCH um.store s " +
      "WHERE um.user.userId = :userId AND um.status = :status " +
      "ORDER BY um.challengeMissionId DESC")
  Page<UserMission> findUserMissionsByStatus(@Param("userId") Long userId,
      @Param("status") UserMissionStatus status,
      Pageable pageable);
}