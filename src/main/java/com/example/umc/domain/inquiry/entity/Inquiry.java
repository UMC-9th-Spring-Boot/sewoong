package com.example.umc.domain.inquiry.entity;

import com.example.umc.domain.inquiry.enums.InquiryType;
import com.example.umc.domain.user.entity.User;
import com.example.umc.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "inquiries")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
public class Inquiry extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "inquiry_id")
  private Long inquiryId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(name = "inquiry_title", nullable = false, length = 100)
  private String inquiryTitle;

  @Column(name = "inquiry_text", nullable = false, columnDefinition = "TEXT")
  private String inquiryText;

  @Enumerated(EnumType.STRING)
  @Column(name = "inquiry_type", nullable = false)
  private InquiryType inquiryType;

  // 연관관계 매핑
  @OneToMany(mappedBy = "inquiry", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Builder.Default
  private List<InquiryImg> inquiryImgs = new ArrayList<>();
}
