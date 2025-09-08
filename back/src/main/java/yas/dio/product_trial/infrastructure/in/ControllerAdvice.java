package yas.dio.product_trial.infrastructure.in;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import yas.dio.product_trial.infrastructure.in.exceptions.ProductValidationException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ProductValidationException.class)
    public ResponseEntity<?> handleProductValidationException(ProductValidationException ex) {
        return new ResponseEntity<>(ex.getErrors(), HttpStatus.BAD_REQUEST);
    }
}
