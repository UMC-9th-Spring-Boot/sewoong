package com.example.umc.domain.mission.entity;

import com.example.umc.domain.mission.enums.Region;
import com.example.umc.domain.store.entity.Store;
import com.example.umc.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "missions")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
public class Mission extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "mission_id")
  private Long missionId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "store_id", nullable = false)
  private Store store;

  @Enumerated(EnumType.STRING)
  @Column(name = "region", nullable = false)
  private Region region;

  @Column(name = "mission_money", nullable = false)
  private Long missionMoney;

  @Column(name = "mission_point", nullable = false)
  private Long missionPoint;
}
