package com.example.umc.domain.member.dto;

import com.example.umc.domain.user.enums.Gender;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {
    public record JoinDTO(
            String name,
            Gender gender,
            LocalDate birth,
            String address,
            String specAddress,
            List<Long> preferCategory
    ) {}
}
