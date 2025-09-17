package yas.dio.product_trial.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yas.dio.product_trial.application.ports.in.GetProductByIdCommand;
import yas.dio.product_trial.application.ports.out.ProductRepository;
import yas.dio.product_trial.domain.exception.ProductNotFoundException;
import yas.dio.product_trial.domain.model.Product;

@Service
@RequiredArgsConstructor
public class ProductQueryService implements GetProductByIdCommand {

    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public Product getById(Long id) {
        if (id == null) {
            return null;
        }
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
}
