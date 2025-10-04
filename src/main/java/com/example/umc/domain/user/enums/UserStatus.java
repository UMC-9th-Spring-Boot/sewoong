package com.example.umc.domain.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {
  ACTIVE("활성"),
  INACTIVE("비활성"),
  WITHDRAWAL("탈퇴");

  private final String value;
}
