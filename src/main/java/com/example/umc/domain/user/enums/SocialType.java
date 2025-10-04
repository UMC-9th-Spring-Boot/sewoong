package com.example.umc.domain.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SocialType {
  KAKAO("카카오"),
  NAVER("네이버"),
  APPLE("애플"),
  GOOGLE("구글");

  private final String value;
}
