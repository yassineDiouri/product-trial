package yas.dio.product_trial.domain.ports.in;

import yas.dio.product_trial.domain.model.Product;

public interface CreateProductCommand {
    /**
     * Create new Product in database
     *
     * @param product to save
     * @return the created product
     */
    Product create(Product product);
}
