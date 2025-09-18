package yas.dio.product_trial.infrastructure.adapters.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import yas.dio.product_trial.domain.exception.ProductNotFoundException;
import yas.dio.product_trial.infrastructure.adapters.in.web.exceptions.ProductValidationException;
import yas.dio.product_trial.infrastructure.adapters.in.web.exceptions.UserValidationException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ProductValidationException.class)
    public ResponseEntity<?> handleProductValidationException(ProductValidationException ex) {
        return new ResponseEntity<>(ex.getErrors(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> handleProductNotFoundException(ProductNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Product not found for id %d".formatted(ex.getId()));
    }

    @ExceptionHandler(UserValidationException.class)
    public ResponseEntity<?> handleUserValidationException(UserValidationException ex) {
        return new ResponseEntity<>(ex.getErrors(), HttpStatus.BAD_REQUEST);
    }
}
