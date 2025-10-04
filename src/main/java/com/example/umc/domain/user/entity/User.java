package com.example.umc.domain.user.entity;

import com.example.umc.domain.user.enums.Gender;
import com.example.umc.domain.user.enums.SocialType;
import com.example.umc.domain.user.enums.UserStatus;
import com.example.umc.domain.user.enums.UserType;
import com.example.umc.domain.inquiry.entity.Inquiry;
import com.example.umc.domain.mission.entity.UserMission;
import com.example.umc.domain.review.entity.Review;
import com.example.umc.domain.notification.entity.Notification;
import com.example.umc.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
public class User extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long userId;

  @Column(name = "name", nullable = false, length = 100)
  private String name;

  @Enumerated(EnumType.STRING)
  @Column(name = "gender", nullable = false)
  private Gender gender;

  @Column(name = "birth")
  private LocalDate birth;

  @Column(name = "address", length = 255)
  private String address;

  @Column(name = "social_id", length = 255)
  private String socialId;

  @Enumerated(EnumType.STRING)
  @Column(name = "social_type")
  private SocialType socialType;

  @Column(name = "user_point")
  @Builder.Default
  private Long userPoint = 0L;

  @Enumerated(EnumType.STRING)
  @Column(name = "user_status", nullable = false)
  @Builder.Default
  private UserStatus userStatus = UserStatus.ACTIVE;

  @Enumerated(EnumType.STRING)
  @Column(name = "user_type")
  private UserType userType;

  @Column(name = "email", length = 255)
  private String email;

  @Column(name = "phone", length = 100)
  private String phone;

  @Column(name = "is_verified")
  @Builder.Default
  private Boolean isVerified = false;

  // 연관관계 매핑
  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Agreement agreement;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Builder.Default
  private List<UserPrefer> userPrefers = new ArrayList<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Builder.Default
  private List<Inquiry> inquiries = new ArrayList<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Builder.Default
  private List<UserMission> userMissions = new ArrayList<>();

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  @Builder.Default
  private List<Review> reviews = new ArrayList<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Builder.Default
  private List<Notification> notifications = new ArrayList<>();
}
