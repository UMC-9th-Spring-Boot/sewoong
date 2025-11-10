package com.example.umc.domain.member.converter;

import com.example.umc.domain.member.dto.MemberReqDTO;
import com.example.umc.domain.member.dto.MemberResDTO;
import com.example.umc.domain.user.entity.User;

public class MemberConverter {

    // Entity -> DTO
    public static MemberResDTO.JoinDTO toJoinDTO(User member) {
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getUserId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    // DTO -> Entity
    public static User toMember(MemberReqDTO.JoinDTO dto) {
        return User.builder()
                .name(dto.name())
                .birth(dto.birth())
                .address(dto.address() != null ? dto.address().toString() : null)
                .gender(dto.gender())
                .build();
    }
}
