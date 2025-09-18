package yas.dio.product_trial.infrastructure.in.dto.requests;

public record LoginRequest(
        String email,
        String password
) {
}
