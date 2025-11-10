package com.example.umc.domain.category.exception;

import com.example.umc.global.apiPayload.code.BaseErrorCode;
import com.example.umc.global.exception.GeneralException;

public class CategoryException extends GeneralException {
    public CategoryException(BaseErrorCode code) {
        super(code);
    }
}
