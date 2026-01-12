package com.example.umc.domain.member.dto;

import com.example.umc.global.auth.Role;
import lombok.Builder;

import java.time.LocalDateTime;

public class MemberResDTO {
    @Builder
    public record JoinDTO(
            Long memberId,
            LocalDateTime createdAt) {
    }

    @Builder
    public record LoginDTO(
            Long memberId,
            String email,
            String name,
            Role role,
            String accessToken,
            LocalDateTime createdAt) {
    }
}
