package yas.dio.product_trial.infrastructure.out;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import yas.dio.product_trial.application.ports.out.UserRepository;
import yas.dio.product_trial.domain.model.User;
import yas.dio.product_trial.infrastructure.out.jpa.UserJpaRepository;
import yas.dio.product_trial.infrastructure.out.jpa.mappers.UserEntityMapper;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    @Transactional
    public User create(User user) {
        return userEntityMapper.toModel(
                userJpaRepository.save(
                        userEntityMapper.toEntity(user)));
    }
}
