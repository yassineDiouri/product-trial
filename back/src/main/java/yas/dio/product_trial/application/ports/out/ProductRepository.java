package yas.dio.product_trial.application.ports.out;

import yas.dio.product_trial.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();

    Optional<Product> findById(Long id);

    Product create(Product product);

    void update(Product product);

    void deleteById(Long id);
}
