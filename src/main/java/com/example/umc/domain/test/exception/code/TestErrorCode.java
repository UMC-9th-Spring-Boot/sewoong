package com.example.umc.domain.test.exception.code;

import com.example.umc.global.apiPayload.code.BaseErrorCode;
import com.example.umc.global.apiPayload.code.ErrorReasonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum TestErrorCode implements BaseErrorCode {

  // For test
  TEST_EXCEPTION(HttpStatus.BAD_REQUEST, "TEST400_1", "이거는 테스트"),
  ;

  private final HttpStatus httpStatus;
  private final String code;
  private final String message;

  @Override
  public ErrorReasonDto getReason() {
    return ErrorReasonDto.builder()
        .message(message)
        .code(code)
        .isSuccess(false)
        .build();
  }

  @Override
  public ErrorReasonDto getReasonHttpStatus() {
    return ErrorReasonDto.builder()
        .httpStatus(httpStatus)
        .message(message)
        .code(code)
        .isSuccess(false)
        .build();
  }
}
