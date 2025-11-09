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
      // 이 부분에서 아까 디폴트 메시지를 초기화 시키고, 새로운 메시지로 덮어씌우게 됩니다.
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(StoreErrorCode.STORE_NOT_FOUND.getMessage())
          .addConstraintViolation();
    }
    return isValid;
  }
}
