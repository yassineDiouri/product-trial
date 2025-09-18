package yas.dio.product_trial.infrastructure.adapters.in.web.mappers;

import org.springframework.stereotype.Component;
import yas.dio.product_trial.domain.model.Product;
import yas.dio.product_trial.infrastructure.adapters.in.web.dto.requests.CreateProductRequest;
import yas.dio.product_trial.infrastructure.adapters.in.web.dto.requests.UpdateProductRequest;
import yas.dio.product_trial.infrastructure.adapters.in.web.dto.responses.ProductResponse;

@Component
public class ProductMapper {

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

    public ProductResponse toResponse(Product model) {
        return new ProductResponse(
                model.getId(),
                model.getCode(),
                model.getName(),
                model.getDescription(),
                model.getImage(),
                model.getCategory(),
                model.getPrice(),
                model.getQuantity(),
                model.getInternalReference(),
                model.getShellId(),
                model.getInventoryStatus(),
                model.getRating(),
                model.getCreatedAt(),
                model.getUpdatedAt()
        );
    }
}
