package yas.dio.product_trial.infrastructure.in.mappers;

import org.springframework.stereotype.Component;
import yas.dio.product_trial.domain.model.Product;
import yas.dio.product_trial.infrastructure.in.dto.request.CreateProductRequest;
import yas.dio.product_trial.infrastructure.in.dto.request.UpdateProductRequest;

@Component
public class ProductRequestMapper {

    public Product toModel(CreateProductRequest request) {
        return Product.builder()
                .code(request.code())
                .name(request.name())
                .description(request.description())
                .image(request.image())
                .category(request.category())
                .price(request.price())
                .quantity(request.quantity())
                .internalReference(request.internalReference())
                .shellId(request.shellId())
                .inventoryStatus(request.inventoryStatus())
                .rating(request.rating())
                .build();
    }

    public Product toModel(UpdateProductRequest request) {
        return Product.builder()
                .id(request.id())
                .code(request.code())
                .name(request.name())
                .description(request.description())
                .image(request.image())
                .category(request.category())
                .price(request.price())
                .quantity(request.quantity())
                .internalReference(request.internalReference())
                .shellId(request.shellId())
                .inventoryStatus(request.inventoryStatus())
                .rating(request.rating())
                .build();
    }
}
