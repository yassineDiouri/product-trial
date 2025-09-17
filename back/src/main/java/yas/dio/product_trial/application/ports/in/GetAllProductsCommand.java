package yas.dio.product_trial.application.ports.in;

import yas.dio.product_trial.domain.model.Product;

public interface GetAllProductsCommand {
    void update(Product product);
}
