package com.one.social_media.exception;

import com.one.social_media.dto.response.ApiResDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalHandlerException {
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ApiResDto> handlingRunTimeException(RuntimeException exception) {
        ApiResDto apiResponse = new ApiResDto<>();
        log.error(exception.toString());
        apiResponse.setMessage(ErrorCode.UNCATEGORIZED.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<ApiResDto> handlingAppException(AppException exception) {
        ApiResDto apiResponse = new ApiResDto<>();

        apiResponse.setMessage(exception.getErrorCode().getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResDto> handlingValidationException(MethodArgumentNotValidException exception) {
        String enumKey = exception.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.valueOf(enumKey);

        ApiResDto apiResponse = new ApiResDto<>();

        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = AuthorizationDeniedException.class)
    public ResponseEntity<ApiResDto> handlingAuthorizationDeniedExceptionException(AuthorizationDeniedException exception) {
        ApiResDto apiResponse = new ApiResDto<>();

        apiResponse.setMessage(exception.getMessage());

        return ResponseEntity.status(401).body(apiResponse);
    }
}
