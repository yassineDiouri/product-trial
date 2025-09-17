package yas.dio.product_trial.infrastructure.out.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import yas.dio.product_trial.infrastructure.out.jpa.entities.UserEntity;

public interface UserJpaRepository extends JpaRepository<UserEntity, String> {
}
