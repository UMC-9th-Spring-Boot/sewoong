package com.example.umc.domain.mission.entity;

import com.example.umc.domain.mission.enums.UserMissionStatus;
import com.example.umc.domain.store.entity.Store;
import com.example.umc.domain.user.entity.User;
import com.example.umc.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "user_missions")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
public class UserMission extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "challenge_mission_id")
  private Long challengeMissionId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "mission_id", nullable = false)
  private Mission mission;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "store_id", nullable = false)
  private Store store;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  @Builder.Default
  private UserMissionStatus status = UserMissionStatus.IN_PROGRESS;

  @Column(name = "challenge_at")
  private java.time.LocalDateTime challengeAt;

  @Column(name = "completed_at")
  private java.time.LocalDateTime completedAt;

  @Column(name = "limited_at")
  private java.time.LocalDateTime limitedAt;

  @Column(name = "success_id", length = 100)
  private String successId;

  // 미션 상태를 완료로 변경
  public void updateStatus(UserMissionStatus status) {
    this.status = status;
    if (status == UserMissionStatus.COMPLETED) {
      this.completedAt = java.time.LocalDateTime.now();
    }
  }
}
