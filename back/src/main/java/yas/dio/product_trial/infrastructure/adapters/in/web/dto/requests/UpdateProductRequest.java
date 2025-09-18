package yas.dio.product_trial.infrastructure.adapters.in.web.dto.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import yas.dio.product_trial.domain.model.InventoryStatus;

public record UpdateProductRequest(
        @NotNull @Min(1) Long id,
        @NotBlank String code,
        @NotBlank String name,
        String description,
        String image,
        @NotBlank String category,
        @Min(value = 0, message = "Price must be greater than or equal to zero")
        double price,
        @Min(value = 0, message = "Quantity must be greater than or equal to zero")
        int quantity,
        @NotBlank String internalReference,
        Long shellId,
        @NotNull InventoryStatus inventoryStatus,
        @Min(1) Double rating
) {
}
