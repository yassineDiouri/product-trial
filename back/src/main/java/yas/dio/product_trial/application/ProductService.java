package yas.dio.product_trial.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yas.dio.product_trial.domain.model.Product;
import yas.dio.product_trial.domain.ports.in.CreateProductCommand;
import yas.dio.product_trial.domain.ports.out.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService implements CreateProductCommand {

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public Product create(Product product) {
        return productRepository.create(product);
    }
}
