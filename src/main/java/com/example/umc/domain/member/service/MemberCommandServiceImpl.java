package com.example.umc.domain.member.service;

import com.example.umc.domain.member.converter.MemberConverter;
import com.example.umc.domain.member.dto.MemberReqDTO;
import com.example.umc.domain.member.dto.MemberResDTO;
import com.example.umc.domain.member.repository.MemberRepository;
import com.example.umc.domain.user.entity.User;
import com.example.umc.domain.user.entity.UserPrefer;
import com.example.umc.domain.user.repository.UserPreferRepository;
import com.example.umc.domain.category.entity.PreferCategory;
import com.example.umc.domain.category.repository.PreferCategoryRepository;
import com.example.umc.domain.category.exception.CategoryException;
import com.example.umc.domain.category.exception.code.CategoryErrorCode;
import lombok.RequiredArgsConstructor;
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

    @Override
    @Transactional
    public MemberResDTO.JoinDTO signup(MemberReqDTO.JoinDTO dto) {
        // 사용자 생성
        User member = MemberConverter.toMember(dto);

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
}
