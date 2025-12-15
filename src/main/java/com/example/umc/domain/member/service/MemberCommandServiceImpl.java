package com.example.umc.domain.member.service;

import com.example.umc.domain.member.converter.MemberConverter;
import com.example.umc.domain.member.dto.MemberReqDTO;
import com.example.umc.domain.member.dto.MemberResDTO;
import com.example.umc.domain.member.exception.MemberException;
import com.example.umc.domain.member.exception.code.MemberErrorCode;
import com.example.umc.domain.member.repository.MemberRepository;
import com.example.umc.domain.user.entity.User;
import com.example.umc.domain.user.entity.UserPrefer;
import com.example.umc.domain.user.repository.UserPreferRepository;
import com.example.umc.domain.category.entity.PreferCategory;
import com.example.umc.domain.category.repository.PreferCategoryRepository;
import com.example.umc.domain.category.exception.CategoryException;
import com.example.umc.domain.category.exception.code.CategoryErrorCode;
import com.example.umc.global.auth.CustomUserDetails;
import com.example.umc.global.auth.JwtUtil;
import com.example.umc.global.auth.Role;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final UserPreferRepository userPreferRepository;
    private final PreferCategoryRepository preferCategoryRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public MemberResDTO.JoinDTO signup(MemberReqDTO.JoinDTO dto) {

        // 비밀번호 암호화
        String salt = passwordEncoder.encode(dto.password());
        // 사용자 생성
        User member = MemberConverter.toMember(dto, salt, Role.ROLE_USER);

        // DB 적용
        memberRepository.save(member);

        // 선호 음식 존재 여부 확인
        if (dto.preferCategory() != null && dto.preferCategory().size() > 0) {
            List<UserPrefer> userPreferList = dto.preferCategory().stream()
                    .map(id -> {
                        // 선호 카테고리 존재 여부 검증
                        PreferCategory preferCategory = preferCategoryRepository.findById(id)
                                .orElseThrow(() -> new CategoryException(CategoryErrorCode.CATEGORY_NOT_FOUND));

                        // UserPrefer 엔티티 생성
                        return UserPrefer.builder()
                                .user(member)
                                .preferCategory(preferCategory)
                                .build();
                    })
                    .collect(Collectors.toList());

            // 모든 선호 음식 추가: DB 적용
            userPreferRepository.saveAll(userPreferList);
        }

        // 응답 DTO 생성
        return MemberConverter.toJoinDTO(member);
    }

    @Override
    @Transactional(readOnly = true)
    public MemberResDTO.LoginDTO login(@Valid MemberReqDTO.LoginDTO dto) {
        // User 조회
        User user = memberRepository.findByEmail(dto.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 비밀번호 검증
        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new MemberException(MemberErrorCode.INVALID_PASSWORD);
        }

        // JWT 토큰 발급용 UserDetails
        CustomUserDetails userDetails = new CustomUserDetails(user);

        // 엑세스 토큰 발급
        String accessToken = jwtUtil.createAccessToken(userDetails);

        // DTO 조립
        return MemberConverter.toLoginDTO(user, accessToken);
    }
}
