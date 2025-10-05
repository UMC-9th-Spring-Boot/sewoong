package com.example.umc.domain.user.entity;

import com.example.umc.domain.category.entity.PreferCategory;
import com.example.umc.global.common.BaseEntity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users_prefer")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@IdClass(UserPreferId.class)
public class UserPrefer extends BaseEntity {

  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", nullable = false)
  private PreferCategory preferCategory;
}
