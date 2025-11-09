package com.example.umc.domain.review.exception;

import com.example.umc.global.apiPayload.code.BaseErrorCode;
import com.example.umc.global.exception.GeneralException;

public class ReviewException extends GeneralException {
    public ReviewException(BaseErrorCode code) {
        super(code);
    }
}
