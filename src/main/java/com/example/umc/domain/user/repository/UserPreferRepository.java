package com.example.umc.domain.user.repository;

import com.example.umc.domain.user.entity.UserPrefer;
import com.example.umc.domain.user.entity.UserPreferId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPreferRepository extends JpaRepository<UserPrefer, UserPreferId> {
}
