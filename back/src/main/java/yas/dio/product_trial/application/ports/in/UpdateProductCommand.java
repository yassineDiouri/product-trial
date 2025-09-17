package yas.dio.product_trial.application.ports.in;

import yas.dio.product_trial.domain.model.Product;

public interface UpdateProductCommand {
    /**
     * Update Product in database using ID
     *
     * @param product to update
     */
    void update(Product product);
}
