package com.example.umc.domain.user.entity;

import com.example.umc.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "agreements")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
public class Agreement extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "agreement_id")
  private Long agreementId;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(name = "is_service_agreed", nullable = false)
  @Builder.Default
  private Boolean isServiceAgreed = false;

  @Column(name = "is_personal_agreed", nullable = false)
  @Builder.Default
  private Boolean isPersonalAgreed = false;

  @Column(name = "is_location_agreed", nullable = false)
  @Builder.Default
  private Boolean isLocationAgreed = false;

  @Column(name = "is_alarm_agreed", nullable = false)
  @Builder.Default
  private Boolean isAlarmAgreed = false;

  @Column(name = "is_fourteen_agreed", nullable = false)
  @Builder.Default
  private Boolean isFourteenAgreed = false;

  // 연관관계 매핑
  @OneToOne(mappedBy = "agreement", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private NotificationAgreement notificationAgreement;
}
