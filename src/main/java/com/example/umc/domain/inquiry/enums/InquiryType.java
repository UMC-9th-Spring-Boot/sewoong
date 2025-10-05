package com.example.umc.domain.inquiry.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InquiryType {
  GENERAL("일반문의"),
  TECHNICAL("기술문의"),
  BUG_REPORT("버그신고"),
  FEATURE_REQUEST("기능요청"),
  ETC("기타");

  private final String value;
}
