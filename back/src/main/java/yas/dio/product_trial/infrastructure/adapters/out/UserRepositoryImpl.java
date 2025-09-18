package yas.dio.product_trial.infrastructure.adapters.out;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import yas.dio.product_trial.application.ports.out.UserRepository;
import yas.dio.product_trial.domain.model.User;
import yas.dio.product_trial.infrastructure.adapters.out.jpa.UserJpaRepository;
import yas.dio.product_trial.infrastructure.adapters.out.jpa.mappers.UserEntityMapper;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email)
                .map(userEntityMapper::toModel);
    }

    @Override
    @Transactional
    public User create(User user) {
        return userEntityMapper.toModel(
                userJpaRepository.save(
                        userEntityMapper.toEntity(user)));
    }
}
