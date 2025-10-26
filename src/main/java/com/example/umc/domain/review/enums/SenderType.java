package com.example.umc.domain.review.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SenderType {
  GENERAL("일반"),
  BOSS("사장"),
  ADMIN("관리자");

  private final String value;
}
