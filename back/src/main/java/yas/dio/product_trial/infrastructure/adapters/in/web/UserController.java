package yas.dio.product_trial.infrastructure.adapters.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yas.dio.product_trial.application.ports.in.CreateUserCommand;
import yas.dio.product_trial.infrastructure.adapters.in.web.dto.requests.CreateUserRequest;
import yas.dio.product_trial.infrastructure.adapters.in.web.exceptions.UserValidationException;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserCommand createUserCommand;

    @PostMapping(value = "account", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody @Validated CreateUserRequest createUserRequest, Errors errors) {
        if (errors.hasErrors()) {
            throw new UserValidationException(errors);
        }
        createUserCommand.createUser(createUserRequest.toModel());
    }
}
