package yas.dio.product_trial.domain.exception;

public class LoginFailureException extends RuntimeException {
    public LoginFailureException(String message) {
        super(message);
    }
}
