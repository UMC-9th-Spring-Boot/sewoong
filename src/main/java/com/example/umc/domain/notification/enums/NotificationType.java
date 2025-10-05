package com.example.umc.domain.notification.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotificationType {
  MISSION_COMPLETED("미션 완료"),
  MISSION_EXPIRED("미션 만료"),
  REVIEW_REPLY("리뷰 답글"),
  INQUIRY_ANSWER("문의 답변"),
  POINT_EARNED("포인트 적립"),
  SYSTEM_NOTICE("시스템 공지"),
  EVENT_NOTIFICATION("이벤트 알림"),
  ETC("기타");

  private final String value;
}
