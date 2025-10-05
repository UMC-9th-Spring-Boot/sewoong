package com.example.umc.domain.inquiry.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inquiry_imgs")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class InquiryImg {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "inquiry_img_id")
  private Long inquiryImgId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "inquiry_id", nullable = false)
  private Inquiry inquiry;

  @Column(name = "inquiry_img", nullable = false, length = 255)
  private String inquiryImg;
}
