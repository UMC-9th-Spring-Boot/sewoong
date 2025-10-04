package com.example.umc.domain.review.entity;

import com.example.umc.domain.review.enums.SenderType;
import com.example.umc.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "review_replies")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
public class ReviewReply extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "reply_id")
  private Long replyId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "review_id", nullable = false)
  private Review review;

  @Column(name = "reply_text", columnDefinition = "TEXT", nullable = false)
  private String replyText;

  @Enumerated(EnumType.STRING)
  @Column(name = "sender_type", nullable = false)
  private SenderType senderType;
}
