package yas.dio.product_trial.infrastructure.adapters.out.jpa.mappers;

import org.springframework.stereotype.Component;
import yas.dio.product_trial.domain.model.User;
import yas.dio.product_trial.infrastructure.adapters.out.jpa.entities.UserEntity;

@Component
public class UserEntityMapper implements EntityMapper<User, UserEntity> {

    @Override
    public User toModel(UserEntity entity) {
        return User.builder()
                .username(entity.getUsername())
                .firstname(entity.getFirstname())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .build();
    }

    @Override
    public UserEntity toEntity(User model) {
        return new UserEntity(
                model.getUsername(),
                model.getFirstname(),
                model.getEmail(),
                model.getPassword()
        );
    }
}
