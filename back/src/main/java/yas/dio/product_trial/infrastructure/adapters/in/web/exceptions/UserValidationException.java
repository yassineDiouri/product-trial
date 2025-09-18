package yas.dio.product_trial.infrastructure.adapters.in.web.exceptions;

import lombok.Getter;
import org.springframework.validation.Errors;

public class UserValidationException extends RuntimeException {
    @Getter
    private final Errors errors;

    public UserValidationException(Errors errors) {
        this.errors = errors;
    }
}
