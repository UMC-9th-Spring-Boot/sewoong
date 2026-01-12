package com.example.umc.domain.member.dto;

import com.example.umc.domain.user.enums.Gender;
import com.example.umc.global.auth.Role;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {
    public record JoinDTO(
            String name,
            Gender gender,
            LocalDate birth,
            String password,
            String email,
            Role role,
            String address,
            String specAddress,
            List<Long> preferCategory) {
    }

    public record LoginDTO(
            String email,
            String password) {
    }
}
