package com.example.umc.domain.member.exception.code;

import com.example.umc.global.apiPayload.code.BaseCode;
import com.example.umc.global.apiPayload.code.ReasonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode implements BaseCode {

    MEMBER_CREATED(HttpStatus.CREATED, "MEMBER201_1", "성공적으로 사용자가 생성되었습니다."),
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
