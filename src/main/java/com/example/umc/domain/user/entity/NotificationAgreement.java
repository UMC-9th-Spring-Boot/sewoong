package com.example.umc.domain.user.entity;

import com.example.umc.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notification_agreements")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class NotificationAgreement extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "notification_agreement_id")
  private Long notificationAgreementId;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "agreement_id", nullable = false)
  private Agreement agreement;

  @Column(name = "evnet_notification", nullable = false)
  @Builder.Default
  private Boolean eventNotification = false;

  @Column(name = "review_notification", nullable = false)
  @Builder.Default
  private Boolean reviewNotification = false;

  @Column(name = "inquiry_notification", nullable = false)
  @Builder.Default
  private Boolean inquiryNotification = false;
}
