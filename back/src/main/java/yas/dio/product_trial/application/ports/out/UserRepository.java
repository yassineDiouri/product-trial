package yas.dio.product_trial.application.ports.out;

import yas.dio.product_trial.domain.model.User;

public interface UserRepository {
    User create(User user);
}
