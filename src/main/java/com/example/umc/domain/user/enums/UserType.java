package com.example.umc.domain.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserType {
  GENERAL("일반"),
  BOSS("사장"),
  ADMIN("관리자");

  private final String value;
}
