package yas.dio.product_trial.application.ports.in;

import yas.dio.product_trial.domain.model.Product;

public interface DeleteProductCommand {
    void delete(Product product);
}
