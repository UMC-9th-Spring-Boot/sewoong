package com.example.umc.domain.mission.validation;

import com.example.umc.domain.mission.validation.validator.MissionExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MissionExistValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistMissions {
  // 여기서 디폴트 메시지를 설정합니다.
  String message() default "해당 미션이 존재하지 않습니다.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
