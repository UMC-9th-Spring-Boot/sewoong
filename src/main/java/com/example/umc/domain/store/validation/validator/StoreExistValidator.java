package com.example.umc.domain.store.validation.validator;

import com.example.umc.domain.store.exception.code.StoreErrorCode;
import com.example.umc.domain.store.repository.StoreRepository;
import com.example.umc.domain.store.validation.ExistStores;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoreExistValidator implements ConstraintValidator<ExistStores, Long> {

  private final StoreRepository storeRepository;

  @Override
  public boolean isValid(Long value, ConstraintValidatorContext context) {
    if (value == null) {
      return true; // null은 @NotNull로 처리
    }

    boolean isValid = storeRepository.existsById(value);
    if (!isValid) {
      // 디폴트 메시지 초기화 및 새로운 메시지로 덮어씌우기
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(StoreErrorCode.STORE_NOT_FOUND.getMessage())
          .addConstraintViolation();
    }
    return isValid;
  }
}
