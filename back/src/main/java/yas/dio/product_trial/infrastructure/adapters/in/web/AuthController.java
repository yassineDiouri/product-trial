package yas.dio.product_trial.infrastructure.adapters.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yas.dio.product_trial.application.AuthService;
import yas.dio.product_trial.infrastructure.adapters.in.web.dto.requests.LoginRequest;
import yas.dio.product_trial.domain.exception.LoginFailureException;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "token", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String login(@RequestBody @Validated LoginRequest loginRequest) {
        if (loginRequest.email() == null || loginRequest.email().trim().isEmpty() ||
                loginRequest.password() == null ||  loginRequest.password().trim().isEmpty()) {
            throw new LoginFailureException("Invalid email or password");
        }
        return authService.login(loginRequest.email(), loginRequest.password());
    }
}
