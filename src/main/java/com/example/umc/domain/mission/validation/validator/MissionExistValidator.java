package com.example.umc.domain.mission.validation.validator;

import com.example.umc.domain.mission.exception.code.MissionErrorCode;
import com.example.umc.domain.mission.repository.MissionRepository;
import com.example.umc.domain.mission.validation.ExistMissions;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MissionExistValidator implements ConstraintValidator<ExistMissions, Long> {

  private final MissionRepository missionRepository;

  @Override
  public boolean isValid(Long value, ConstraintValidatorContext context) {
    if (value == null) {
      return true; // null은 @NotNull로 처리
    }

    boolean isValid = missionRepository.existsById(value);
    if (!isValid) {
      // 디폴트 메시지 초기화 및 새로운 메시지로 덮어씌우기
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(MissionErrorCode.MISSION_NOT_FOUND.getMessage())
          .addConstraintViolation();
    }
    return isValid;
  }
}
