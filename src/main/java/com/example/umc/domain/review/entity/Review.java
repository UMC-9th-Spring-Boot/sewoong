package com.example.umc.domain.review.entity;

import com.example.umc.domain.store.entity.Store;
import com.example.umc.domain.user.entity.User;
import com.example.umc.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reviews")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
public class Review extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "review_id")
  private Long reviewId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "store_id", nullable = false)
  private Store store;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(name = "review_text", columnDefinition = "TEXT")
  private String reviewText;

  @Column(name = "score", precision = 2, scale = 1)
  private BigDecimal score;

  // 연관관계 매핑
  @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Builder.Default
  private List<ReviewImg> reviewImgs = new ArrayList<>();

  @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Builder.Default
  private List<ReviewReply> reviewReplies = new ArrayList<>();
}
