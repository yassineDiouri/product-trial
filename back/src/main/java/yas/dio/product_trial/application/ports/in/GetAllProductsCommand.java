package yas.dio.product_trial.application.ports.in;

import yas.dio.product_trial.domain.model.Product;

import java.util.List;

public interface GetAllProductsCommand {
    List<Product> getAll();
}
