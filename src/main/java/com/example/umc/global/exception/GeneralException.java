package com.example.umc.global.exception;

import com.example.umc.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {

  private BaseErrorCode codeBase;

  public GeneralException(BaseErrorCode codeBase) {
    super(codeBase.getReason().getMessage());
    this.codeBase = codeBase;
  }
}
