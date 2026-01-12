package com.example.umc.global.auth;

import com.example.umc.global.apiPayload.ApiResponse;
import com.example.umc.global.apiPayload.code.status.ErrorStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void commence(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException) throws IOException {
    response.setContentType("application/json;charset=UTF-8");
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    ApiResponse<Void> errorResponse = ApiResponse.onFailure(
        ErrorStatus._UNAUTHORIZED.getReasonHttpStatus().getCode(),
        ErrorStatus._UNAUTHORIZED.getReasonHttpStatus().getMessage(),
        null);

    objectMapper.writeValue(response.getOutputStream(), errorResponse);
  }
}
