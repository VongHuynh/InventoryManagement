package com.management.InventoryManagement.ExceptionHandle;

import com.management.InventoryManagement.Payload.Response.ObjectResponse;
import io.jsonwebtoken.SignatureException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice //collect all exception
public class ValidationHandler extends ResponseEntityExceptionHandler {
    @Override //custom exception handler
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
            logger.error(ex.getMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ObjectResponse(status.value(),
                errors.toString(), false, null));
    }

    @ExceptionHandler(ConstraintViolationException.class) //endpoint exception handler error
    protected ResponseEntity<Object> handlePathVariableError(final ConstraintViolationException exception) {
        String[] s = exception.getMessage().split(",");
        Map<String, String> errors = new HashMap<>();
        for (String error : s) {
            String filed = error.substring(error.indexOf(".") + 1, error.indexOf(":")).trim();
            String message = error.substring(error.indexOf(":") + 1).trim();
            errors.put(filed, message);
            logger.error(exception.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ObjectResponse(HttpStatus.BAD_REQUEST.value(),
                errors.toString(), false, null));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleNotFoundException(Exception e){
        logger.error("Not Found exception: " + e.getMessage());
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ObjectResponse(HttpStatus.NOT_FOUND.value(),
                "Not Found", false, null ));
    }

    @ExceptionHandler(Exception.class) //ApplicationException handler error
    public ResponseEntity<String> handleUnwantedException(Exception e) {
        logger.error("Unwanted exception: " + e.getMessage());
        e.printStackTrace();
        return ResponseEntity.status(500).body("Server Error");
    }
}
