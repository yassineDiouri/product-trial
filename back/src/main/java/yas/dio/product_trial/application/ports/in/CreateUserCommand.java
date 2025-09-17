package yas.dio.product_trial.application.ports.in;

import yas.dio.product_trial.domain.model.User;

public interface CreateUserCommand {
    /**
     * Create new user
     *
     * @param user to be created
     */
    void createUser(User user);
}
