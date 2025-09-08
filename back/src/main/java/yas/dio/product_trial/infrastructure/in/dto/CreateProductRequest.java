package yas.dio.product_trial.infrastructure.in.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import yas.dio.product_trial.domain.model.InventoryStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {
    @NotBlank
    private String code;
    @NotBlank
    private String name;
    private String description;
    private String image;
    @NotBlank
    private String category;
    @Min(value = 0, message = "Price must be greater than or equal to zero")
    private double price;
    @Min(value = 0, message = "Quantity must be greater than or equal to zero")
    private int quantity;
    @NotBlank
    private String internalReference;
    private Long shellId;
    @NotNull
    private InventoryStatus inventoryStatus;
    private Double rating;
}
