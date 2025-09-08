package yas.dio.product_trial.infrastructure.out;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import yas.dio.product_trial.domain.model.Product;
import yas.dio.product_trial.domain.ports.out.ProductRepository;
import yas.dio.product_trial.infrastructure.out.jpa.ProductJpaRepository;
import yas.dio.product_trial.infrastructure.out.jpa.mappers.ProductEntityMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository repository;
    private final ProductEntityMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return new ArrayList<>();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public Product create(Product product) {
        return mapper.toModel(repository.save(mapper.toEntity(product)));
    }

    @Override
    @Transactional
    public void update(Product product) {

    }

    @Override
    @Transactional
    public void deleteById(Long id) {

    }
}
