package yas.dio.product_trial.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yas.dio.product_trial.application.ports.in.LoginCommand;
import yas.dio.product_trial.application.ports.out.TokenProvider;
import yas.dio.product_trial.application.ports.out.UserRepository;
import yas.dio.product_trial.domain.model.User;
import yas.dio.product_trial.domain.exception.LoginFailureException;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService implements LoginCommand {

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    @Override
    public String login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty() || !user.get().getPassword().equals(password)) {
            throw new LoginFailureException("Invalid email or password");
        }
        return tokenProvider.generateToken(email, Map.of("username", user.get().getUsername()));
    }
}
