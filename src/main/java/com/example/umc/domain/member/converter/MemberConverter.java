package com.example.umc.domain.member.converter;

import com.example.umc.domain.member.dto.MemberReqDTO;
import com.example.umc.domain.member.dto.MemberResDTO;
import com.example.umc.domain.user.entity.User;
import com.example.umc.global.auth.Role;

public class MemberConverter {

    // Entity -> DTO
    public static MemberResDTO.JoinDTO toJoinDTO(User member) {
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getUserId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    // Entity -> LoginDTO
    /*
    public static MemberResDTO.LoginDTO toLoginDTO(User member) {
        return MemberResDTO.LoginDTO.builder()
                .memberId(member.getUserId())
                .email(member.getEmail())
                .name(member.getName())
                .role(member.getRole())
                .createdAt(member.getCreatedAt())
                .build();
    }
    */

    // Entity + AccessToken -> LoginDTO
    public static MemberResDTO.LoginDTO toLoginDTO(User member, String accessToken) {
        return MemberResDTO.LoginDTO.builder()
                .memberId(member.getUserId())
                .email(member.getEmail())
                .name(member.getName())
                .role(member.getRole())
                .accessToken(accessToken)
                .createdAt(member.getCreatedAt())
                .build();
    }

    // DTO -> Entity
    public static User toMember(MemberReqDTO.JoinDTO dto, String salt, Role role) {
        return User.builder()
                .name(dto.name())
                .birth(dto.birth())
                .address(dto.address() != null ? dto.address().toString() : null)
                .password(salt)
                .role(dto.role())
                .email(dto.email())
                .address(dto.address())
                .gender(dto.gender())
                .build();
    }
}
