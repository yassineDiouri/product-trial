package yas.dio.product_trial.infrastructure.adapters.out.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import yas.dio.product_trial.infrastructure.adapters.out.jpa.entities.ProductEntity;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
}
