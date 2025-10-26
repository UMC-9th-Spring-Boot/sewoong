package com.example.umc.domain.user.entity;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPreferId implements Serializable {
  private Long user;
  private Long preferCategory;
}
