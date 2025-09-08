package yas.dio.product_trial.domain.ports.in;

import yas.dio.product_trial.domain.model.Product;

public interface UpdateProductCommand {
    void update(Product product);
}
