package com.example.umc.global.auth;

import com.example.umc.domain.member.repository.MemberRepository;
import com.example.umc.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // 검증할 Member 조회
    User user = memberRepository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("해당 사용자를 찾지 못했습니다."));

    // CustomUserDetails 반환
    return new CustomUserDetails(user);
  }
}
