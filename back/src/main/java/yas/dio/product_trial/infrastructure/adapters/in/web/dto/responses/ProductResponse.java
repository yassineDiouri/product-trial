package yas.dio.product_trial.infrastructure.adapters.in.web.dto.responses;


import yas.dio.product_trial.domain.model.InventoryStatus;

import java.time.LocalDateTime;

public record ProductResponse(
        long id,
        String code,
        String name,
        String description,
        String image,
        String category,
        double price,
        int quantity,
        String internalReference,
        long shellId,
        InventoryStatus inventoryStatus,
        Double rating,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
