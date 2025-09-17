package yas.dio.product_trial.infrastructure.out;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import yas.dio.product_trial.domain.model.Product;
import yas.dio.product_trial.application.ports.out.ProductRepository;
import yas.dio.product_trial.infrastructure.out.jpa.ProductJpaRepository;
import yas.dio.product_trial.infrastructure.out.jpa.mappers.ProductEntityMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository jpaRepository;
    private final ProductEntityMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return new ArrayList<>();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toModel);
    }

    @Override
    @Transactional
    public Product create(Product product) {
        return mapper.toModel(jpaRepository.save(mapper.toEntity(product)));
    }

    @Override
    @Transactional
    public void update(Product product) {
        if (product.getId() != null) {
            jpaRepository.findById(product.getId()).ifPresent(entity -> {
                entity.setCode(product.getCode());
                entity.setName(product.getName());
                entity.setDescription(product.getDescription());
                entity.setImage(product.getImage());
                entity.setCategory(product.getCategory());
                entity.setPrice(product.getPrice());
                entity.setQuantity(product.getQuantity());
                entity.setInternalReference(product.getInternalReference());
                entity.setShellId(product.getShellId());
                entity.setInventoryStatus(product.getInventoryStatus() == null ? null : product.getInventoryStatus().name());
                entity.setRating(product.getRating());
                entity.setCreatedAt(product.getCreatedAt());
                entity.setUpdatedAt(LocalDateTime.now());
            });
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {

    }
}
