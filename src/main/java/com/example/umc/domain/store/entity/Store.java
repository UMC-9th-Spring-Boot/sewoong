package com.example.umc.domain.store.entity;

import com.example.umc.domain.store.enums.StoreType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stores")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Store {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "store_id")
  private Long storeId;

  @Column(name = "store_name", nullable = false, length = 100)
  private String storeName;

  @Column(name = "store_address", nullable = false, length = 255)
  private String storeAddress;

  @Enumerated(EnumType.STRING)
  @Column(name = "store_type", nullable = false)
  private StoreType storeType;

  @Column(name = "store_score", precision = 2, scale = 1)
  private BigDecimal storeScore;

  // 연관관계 매핑
  @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Builder.Default
  private List<StoreImg> storeImgs = new ArrayList<>();

  @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Builder.Default
  private List<com.example.umc.domain.mission.entity.Mission> missions = new ArrayList<>();
}
