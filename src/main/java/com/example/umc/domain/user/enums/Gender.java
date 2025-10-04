package com.example.umc.domain.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {
  NONE("선택안함"),
  MALE("남"),
  FEMALE("여");

  private final String value;
}
