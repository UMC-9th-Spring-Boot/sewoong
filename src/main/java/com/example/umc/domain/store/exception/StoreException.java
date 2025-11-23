package com.example.umc.domain.store.exception;

import com.example.umc.global.apiPayload.code.BaseErrorCode;
import com.example.umc.global.exception.GeneralException;

public class StoreException extends GeneralException {
    public StoreException(BaseErrorCode code) {
        super(code);
    }
}
