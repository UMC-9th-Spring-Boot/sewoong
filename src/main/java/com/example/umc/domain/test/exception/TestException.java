package com.example.umc.domain.test.exception;

import com.example.umc.global.exception.GeneralException;

public class TestException extends GeneralException {

  public TestException(com.example.umc.domain.test.exception.code.TestErrorCode errorCode) {
    super(errorCode);
  }
}
