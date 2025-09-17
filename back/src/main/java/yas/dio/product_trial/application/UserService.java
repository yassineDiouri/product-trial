package yas.dio.product_trial.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yas.dio.product_trial.application.ports.in.CreateUserCommand;
import yas.dio.product_trial.application.ports.out.UserRepository;
import yas.dio.product_trial.domain.model.User;

@Service
@RequiredArgsConstructor
public class UserService implements CreateUserCommand {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void createUser(User user) {
        userRepository.create(user);
    }
}
