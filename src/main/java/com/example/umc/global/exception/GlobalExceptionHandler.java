package com.example.umc.global.exception;

import com.example.umc.global.apiPayload.ApiResponse;
import com.example.umc.global.apiPayload.code.ErrorReasonDto;
import com.example.umc.global.apiPayload.code.status.ErrorStatus;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler
  public ResponseEntity<Object> general(GeneralException e, WebRequest request) {
    ErrorReasonDto reasonHttpStatus = e.getCodeBase().getReasonHttpStatus();
    return handleExceptionInternal(e, reasonHttpStatus, HttpHeaders.EMPTY, reasonHttpStatus.getHttpStatus(), request);
  }

  @ExceptionHandler
  public ResponseEntity<Object> exception(Exception e, WebRequest request) {
    return handleExceptionInternalFalse(e,
        ApiResponse.onFailure(
            ErrorStatus._INTERNAL_SERVER_ERROR.getReasonHttpStatus().getCode(),
            ErrorStatus._INTERNAL_SERVER_ERROR.getReasonHttpStatus().getMessage(),
            null),
        HttpHeaders.EMPTY, HttpStatus.INTERNAL_SERVER_ERROR, request, e.getMessage());
  }

  @Override
  public ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException e, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    Map<String, String> errors = new LinkedHashMap<>();

    e.getBindingResult().getFieldErrors().stream()
        .forEach(fieldError -> {
          String fieldName = fieldError.getField();
          String errorMessage = Optional.ofNullable(fieldError.getDefaultMessage()).orElse("");
          errors.merge(fieldName, errorMessage, (existingMessage, newMessage) -> existingMessage + ", " + newMessage);
        });

    return handleExceptionInternalArgs(e, HttpHeaders.EMPTY, errors, HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler
  public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e, WebRequest request) {
    String errorMessage = e.getConstraintViolations().stream()
        .map(constraintViolation -> constraintViolation.getMessage())
        .findFirst()
        .orElseThrow(() -> new RuntimeException("ConstraintViolationException translating unexpected"));

    return handleExceptionInternalFalse(e, null, HttpHeaders.EMPTY, HttpStatus.BAD_REQUEST, request, errorMessage);
  }

  protected ResponseEntity<Object> handleExceptionInternal(Exception e, Object body,
      HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

    // body가 null인 경우 기본 에러 응답 반환
    if (body == null) {
      return handleExceptionInternalFalse(e,
          ApiResponse.onFailure(
              ErrorStatus._INTERNAL_SERVER_ERROR.getReasonHttpStatus().getCode(),
              ErrorStatus._INTERNAL_SERVER_ERROR.getReasonHttpStatus().getMessage(),
              null),
          headers, statusCode, request, e.getMessage());
    }

    ErrorReasonDto errorReason = (ErrorReasonDto) body;
    return handleExceptionInternalFalse(e, ApiResponse.onFailure(
        errorReason.getCode(),
        errorReason.getMessage(),
        null), headers, statusCode, request, errorReason.getMessage());
  }

  private ResponseEntity<Object> handleExceptionInternalArgs(Exception e, HttpHeaders headers,
      Map<String, String> errors,
      HttpStatusCode statusCode, WebRequest request) {
    return handleExceptionInternalFalse(e, ApiResponse.onFailure("COMMON400", "잘못된 요청입니다.", errors), headers,
        statusCode, request, errors.toString());
  }

  private ResponseEntity<Object> handleExceptionInternalFalse(Exception e, Object body,
      HttpHeaders headers, HttpStatusCode statusCode, WebRequest request, String errorPoint) {
    ServletWebRequest servletWebRequest = (ServletWebRequest) request;
    String url = servletWebRequest.getRequest().getRequestURI();
    log.error("Rest API Exception: {}, url: {}, message: {}", statusCode, url, errorPoint, e);

    return super.handleExceptionInternal(e, body, headers, statusCode, request);
  }
}
