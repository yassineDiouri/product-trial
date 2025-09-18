package yas.dio.product_trial.infrastructure.adapters.in.web.dto.requests;

public record LoginRequest(
        String email,
        String password
) {
}
