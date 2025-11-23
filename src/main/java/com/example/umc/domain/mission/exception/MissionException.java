package com.example.umc.domain.mission.exception;

import com.example.umc.global.apiPayload.code.BaseErrorCode;
import com.example.umc.global.exception.GeneralException;

public class MissionException extends GeneralException {
    public MissionException(BaseErrorCode code) {
        super(code);
    }
}
