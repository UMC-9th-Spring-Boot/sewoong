package com.example.umc.domain.test.converter;

import com.example.umc.domain.test.dto.TestResDTO;

public class TestConverter {

  // 객체 -> DTO
  public static TestResDTO.Testing toTestingDTO(String testing) {
    return TestResDTO.Testing.builder()
        .testString(testing)
        .build();
  }

  // 객체 -> DTO
  public static TestResDTO.Exception toExceptionDTO(String testing) {
    return TestResDTO.Exception.builder()
        .testString(testing)
        .build();
  }
}
