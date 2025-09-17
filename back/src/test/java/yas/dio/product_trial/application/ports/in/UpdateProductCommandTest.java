package yas.dio.product_trial.application.ports.in;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;
import yas.dio.product_trial.application.ports.out.ProductRepository;
import yas.dio.product_trial.domain.model.InventoryStatus;
import yas.dio.product_trial.domain.model.Product;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
@Transactional
@DisplayName("UpdateProductCommand Tests")
class UpdateProductCommandTest {

    @Autowired
    private UpdateProductCommand updateProduct;
    @Autowired
    private ProductRepository productRepository;

    @Test
    void givenValidProductWithId_WhenUpdateProduct_ThenDatabaseUpdated() {
        // Given
        /// Create Product
        Product initialProduct = Product.builder()
                .code("PROD001")
                .name("Initial Product")
                .description("Initial description")
                .image("initial-image.jpg")
                .category("Electronics")
                .price(100.0)
                .quantity(50)
                .internalReference("REF001")
                .shellId(1L)
                .inventoryStatus(InventoryStatus.INSTOCK)
                .rating(4.0)
                .build();

        Product savedProduct = productRepository.create(initialProduct);
        Long productId = savedProduct.getId();

        /// Product with updated data
        Product updatedProduct = Product.builder()
                .id(productId)
                .code("PROD001-UPDATED")
                .name("Updated Product")
                .description("Updated description")
                .image("updated-image.jpg")
                .category("Home & Garden")
                .price(150.0)
                .quantity(25)
                .internalReference("REF001-UPD")
                .shellId(2L)
                .inventoryStatus(InventoryStatus.LOWSTOCK)
                .rating(4.5)
                .createdAt(savedProduct.getCreatedAt())
                .updatedAt(savedProduct.getUpdatedAt())
                .build();
        // When
        updateProduct.update(updatedProduct);
        //Then
        Optional<Product> retrievedProduct = productRepository.findById(productId);

        assertThat(retrievedProduct).isPresent();
        Product actualProduct = retrievedProduct.get();

        assertThat(actualProduct.getId()).isEqualTo(productId);
        assertThat(actualProduct.getCode()).isEqualTo("PROD001-UPDATED");
        assertThat(actualProduct.getName()).isEqualTo("Updated Product");
        assertThat(actualProduct.getDescription()).isEqualTo("Updated description");
        assertThat(actualProduct.getImage()).isEqualTo("updated-image.jpg");
        assertThat(actualProduct.getCategory()).isEqualTo("Home & Garden");
        assertThat(actualProduct.getPrice()).isEqualTo(150.0);
        assertThat(actualProduct.getQuantity()).isEqualTo(25);
        assertThat(actualProduct.getInternalReference()).isEqualTo("REF001-UPD");
        assertThat(actualProduct.getShellId()).isEqualTo(2L);
        assertThat(actualProduct.getInventoryStatus()).isEqualTo(InventoryStatus.LOWSTOCK);
        assertThat(actualProduct.getRating()).isEqualTo(4.5);
        assertThat(actualProduct.getCreatedAt()).isEqualTo(savedProduct.getCreatedAt());
        assertThat(actualProduct.getUpdatedAt()).isAfter(savedProduct.getUpdatedAt());
    }
}