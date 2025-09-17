package yas.dio.product_trial.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yas.dio.product_trial.application.ports.in.CreateProductCommand;
import yas.dio.product_trial.application.ports.in.UpdateProductCommand;
import yas.dio.product_trial.application.ports.out.ProductRepository;
import yas.dio.product_trial.domain.model.Product;

@Service
@RequiredArgsConstructor
public class ProductService implements CreateProductCommand, UpdateProductCommand {

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public Product create(Product product) {
        return productRepository.create(product);
    }

    @Override
    public void update(Product product) {
        productRepository.update(product);
    }
}
