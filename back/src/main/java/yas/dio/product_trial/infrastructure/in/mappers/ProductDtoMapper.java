package yas.dio.product_trial.infrastructure.in.mappers;

import org.springframework.stereotype.Component;
import yas.dio.product_trial.domain.model.Product;
import yas.dio.product_trial.infrastructure.in.dto.CreateProductRequest;

@Component
public class ProductDtoMapper {

    public Product toModel(CreateProductRequest request) {
        return Product.builder()
                .code(request.getCode())
                .name(request.getName())
                .description(request.getDescription())
                .image(request.getImage())
                .category(request.getCategory())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .internalReference(request.getInternalReference())
                .shellId(request.getShellId())
                .inventoryStatus(request.getInventoryStatus())
                .rating(request.getRating())
                .build();
    }
}
