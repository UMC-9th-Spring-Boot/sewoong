package com.example.umc.domain.store.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StoreType {
  KOREAN("한식"),
  CHINESE("중식"),
  JAPANESE("일식"),
  WESTERN("양식"),
  CHICKEN("치킨"),
  SNACK("분식"),
  MEAT("고기/구이"),
  DOSIRAK("도시락"),
  YASICK("야식"),
  DESSERT("디저트"),
  FAST_FOOD("패스트푸드"),
  ASIAN("아시안푸드"),
  ETC("기타");

  private final String value;
}
