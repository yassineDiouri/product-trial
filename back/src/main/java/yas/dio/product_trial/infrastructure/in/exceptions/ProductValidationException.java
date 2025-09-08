package yas.dio.product_trial.infrastructure.in.exceptions;

import lombok.Getter;
import org.springframework.validation.Errors;

public class ProductValidationException extends RuntimeException {

    @Getter
    private final Errors errors;

    public ProductValidationException(Errors errors) {
        this.errors = errors;
    }
}
