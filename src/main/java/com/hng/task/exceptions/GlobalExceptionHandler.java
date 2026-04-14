package com.hng.task.exceptions;

import com.hng.task.util.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomBadRequestException.class)
    public ResponseEntity<ApiResponse<String>> handleCustomBadRequestException(CustomBadRequestException ex){

        return ResponseEntity.badRequest().body(ApiResponse.failure(ex.getMessage()));
    }

    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleCustomNotFoundException(CustomNotFoundException ex){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.failure(ex.getMessage()));
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<ApiResponse<String>> handleUnprocessableEntityException(UnprocessableEntityException ex) {

        return ResponseEntity.unprocessableContent().body(ApiResponse.failure(ex.getMessage()));

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleMethodArgumentException(MethodArgumentNotValidException ex) {

        String message = ex.getBindingResult()
                .getFieldErrors().stream().map(
                        fieldError -> fieldError.getDefaultMessage())
                .findFirst()
                .orElse("Validation failed");

        return ResponseEntity.badRequest().body(ApiResponse.failure(message));
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<ApiResponse<String>> handleRestClientException(RestClientException ex) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(ApiResponse.failure("External API error: " + ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleInternalServerError(Exception ex) {
        return ResponseEntity.internalServerError().body(ApiResponse.failure("An error occurred: " + ex.getMessage()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<String>> handleConstraintViolationException(ConstraintViolationException ex){

        String errorMessage = ex.getConstraintViolations().iterator().next().getMessage();
        return ResponseEntity.badRequest().body(ApiResponse.failure(errorMessage));
    }
}
