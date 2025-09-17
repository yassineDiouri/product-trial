package yas.dio.product_trial.infrastructure.out.jpa.mappers;

import org.springframework.stereotype.Component;
import yas.dio.product_trial.domain.model.InventoryStatus;
import yas.dio.product_trial.domain.model.Product;
import yas.dio.product_trial.infrastructure.out.jpa.entities.ProductEntity;

@Component
public class ProductEntityMapper implements EntityMapper<Product, ProductEntity> {

    public Product toModel(ProductEntity entity) {
        return Product.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .name(entity.getName())
                .description(entity.getDescription())
                .image(entity.getImage())
                .category(entity.getCategory())
                .price(entity.getPrice())
                .quantity(entity.getQuantity())
                .internalReference(entity.getInternalReference())
                .shellId(entity.getShellId())
                .inventoryStatus(InventoryStatus.valueOf(entity.getInventoryStatus()))
                .rating(entity.getRating())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public ProductEntity toEntity(Product model) {
        ProductEntity entity = new ProductEntity();
        entity.setId(model.getId());
        entity.setCode(model.getCode());
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        entity.setImage(model.getImage());
        entity.setCategory(model.getCategory());
        entity.setPrice(model.getPrice());
        entity.setQuantity(model.getQuantity());
        entity.setInternalReference(model.getInternalReference());
        entity.setShellId(model.getShellId());
        entity.setInventoryStatus(model.getInventoryStatus() == null ? null : model.getInventoryStatus().name());
        entity.setRating(model.getRating());
        entity.setCreatedAt(model.getCreatedAt());
        entity.setUpdatedAt(model.getUpdatedAt());
        return entity;
    }
}
