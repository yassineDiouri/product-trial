package yas.dio.product_trial.application.ports.out;

import yas.dio.product_trial.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByEmail(String email);

    User create(User user);
}
