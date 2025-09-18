package yas.dio.product_trial.infrastructure.adapters.in.web.dto.requests;

import jakarta.validation.constraints.NotBlank;
import yas.dio.product_trial.domain.model.User;

public record CreateUserRequest(
        @NotBlank String username,
        @NotBlank String firstname,
        @NotBlank String email,
        @NotBlank String password
) {
    public User toModel() {
        return new User(username, firstname, email, password);
    }
}
