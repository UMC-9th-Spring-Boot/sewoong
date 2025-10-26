package com.example.umc.domain.user.repository;

import com.example.umc.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  // 1. 마이페이지 화면 쿼리 - 메서드 이름으로 쿼리 생성
  Optional<User> findByUserId(Long userId);

  // 2. 활성 사용자만 조회 (deletedAt이 null인 사용자)
  @Query("SELECT u FROM User u WHERE u.userId = :userId AND u.deletedAt IS NULL")
  Optional<User> findActiveUserById(@Param("userId") Long userId);
}
