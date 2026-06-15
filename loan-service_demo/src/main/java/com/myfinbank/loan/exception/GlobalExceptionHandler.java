package com.myfinbank.loan.exception;

import feign.FeignException;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleVehicleNotFound(VehicleNotFoundException exception) {
        return buildResponse(HttpStatus.NOT_FOUND, "Vehicle Not Found", exception.getMessage());
    }

    @ExceptionHandler(LoanNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleLoanNotFound(LoanNotFoundException exception) {
        return buildResponse(HttpStatus.NOT_FOUND, "Loan Not Found", exception.getMessage());
    }

    @ExceptionHandler(DocumentNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleDocumentNotFound(DocumentNotFoundException exception) {
        return buildResponse(HttpStatus.NOT_FOUND, "Document Not Found", exception.getMessage());
    }

    @ExceptionHandler(InvalidLoanApplicationException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidLoan(InvalidLoanApplicationException exception) {
        return buildResponse(HttpStatus.BAD_REQUEST, "Invalid Loan Application", exception.getMessage());
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Map<String, Object>> handleFeignException(FeignException exception) {
        return buildResponse(
                HttpStatus.SERVICE_UNAVAILABLE,
                "Dependent Service Unavailable",
                "Customer service is unavailable. Please try again later."
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();

        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Validation Failed");
        response.put("messages", errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception exception) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", exception.getMessage());
    }

    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String error, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", status.value());
        response.put("error", error);
        response.put("message", message);

        return new ResponseEntity<>(response, status);
    }
}