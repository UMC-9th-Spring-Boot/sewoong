package com.example.umc.domain.mission.exception.code;

import com.example.umc.global.apiPayload.code.BaseCode;
import com.example.umc.global.apiPayload.code.ReasonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseCode {

    MISSION_CHALLENGED(HttpStatus.CREATED, "MISSION201_1", "미션 도전이 성공적으로 등록되었습니다."),
    MISSION_CREATED(HttpStatus.CREATED, "MISSION201_2", "미션이 성공적으로 생성되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

    @Override
    public ReasonDto getReason() {
        return ReasonDto.builder()
                .isSuccess(true)
                .code(code)
                .message(message)
                .build();
    }

    @Override
    public ReasonDto getReasonHttpStatus() {
        return ReasonDto.builder()
                .isSuccess(true)
                .httpStatus(status)
                .code(code)
                .message(message)
                .build();
    }
}
