package yas.dio.product_trial.application.ports.in;

import yas.dio.product_trial.domain.model.Product;

public interface GetProductByIdCommand {
    void update(Product product);
}
