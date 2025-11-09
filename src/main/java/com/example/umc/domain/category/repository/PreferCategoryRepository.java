package com.example.umc.domain.category.repository;

import com.example.umc.domain.category.entity.PreferCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferCategoryRepository extends JpaRepository<PreferCategory, Long> {
}
