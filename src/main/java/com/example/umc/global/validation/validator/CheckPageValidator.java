package com.example.umc.global.validation.validator;

import com.example.umc.global.validation.CheckPage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class CheckPageValidator implements ConstraintValidator<CheckPage, Integer> {

  @Override
  public boolean isValid(Integer value, ConstraintValidatorContext context) {
    if (value == null) {
      return true; // null은 @NotNull로 처리
    }

    // 페이지 번호가 1 미만인 경우 검증 실패
    return value >= 1;
  }
}
