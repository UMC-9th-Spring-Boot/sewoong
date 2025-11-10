package com.example.umc.domain.user.exception;

import com.example.umc.global.apiPayload.code.BaseErrorCode;
import com.example.umc.global.exception.GeneralException;

public class UserException extends GeneralException {
    public UserException(BaseErrorCode code) {
        super(code);
    }
}
