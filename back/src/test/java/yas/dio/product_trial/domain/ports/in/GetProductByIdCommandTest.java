package yas.dio.product_trial.domain.ports.in;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;
import yas.dio.product_trial.application.ports.in.GetProductByIdCommand;
import yas.dio.product_trial.application.ports.out.ProductRepository;
import yas.dio.product_trial.domain.exception.ProductNotFoundException;
import yas.dio.product_trial.domain.model.InventoryStatus;
import yas.dio.product_trial.domain.model.Product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
@Transactional
@DisplayName("GetProductByIdCommand Tests")
class GetProductByIdCommandTest {

    @Autowired
    private GetProductByIdCommand getProductByIdCommand;
    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("Given valid product ID, when get product by ID, then return correct product")
    void givenValidProductId_WhenGetProductById_ThenReturnCorrectProduct() {
        // Given
        Product expectedProduct = Product.builder()
                .code("PROD001")
                .name("Test Product")
                .description("Test description")
                .image("test-image.jpg")
                .category("Electronics")
                .price(99.99)
                .quantity(10)
                .internalReference("REF001")
                .shellId(1L)
                .inventoryStatus(InventoryStatus.INSTOCK)
                .rating(4.2)
                .build();

        Product savedProduct = productRepository.create(expectedProduct);
        Long productId = savedProduct.getId();

        // When
        Product actualProduct = getProductByIdCommand.getById(productId);

        // Then
        assertThat(actualProduct).isNotNull();
        assertThat(actualProduct.getId()).isEqualTo(productId);
        assertThat(actualProduct.getCode()).isEqualTo("PROD001");
        assertThat(actualProduct.getName()).isEqualTo("Test Product");
        assertThat(actualProduct.getDescription()).isEqualTo("Test description");
        assertThat(actualProduct.getImage()).isEqualTo("test-image.jpg");
        assertThat(actualProduct.getCategory()).isEqualTo("Electronics");
        assertThat(actualProduct.getPrice()).isEqualTo(99.99);
        assertThat(actualProduct.getQuantity()).isEqualTo(10);
        assertThat(actualProduct.getInternalReference()).isEqualTo("REF001");
        assertThat(actualProduct.getShellId()).isEqualTo(1L);
        assertThat(actualProduct.getInventoryStatus()).isEqualTo(InventoryStatus.INSTOCK);
        assertThat(actualProduct.getRating()).isEqualTo(4.2);
        assertThat(actualProduct.getCreatedAt()).isNotNull();
        assertThat(actualProduct.getUpdatedAt()).isNotNull();
    }

    @Test
    @DisplayName("Given non-existent product ID, when get product by ID, then throw exception")
    void givenNonExistentProductId_WhenGetProductById_ThenThrowException() {
        // Given
        Long nonExistentId = 999L;

        // When & Then
        assertThrows(ProductNotFoundException.class,
                () -> getProductByIdCommand.getById(nonExistentId));
    }

    @Test
    @DisplayName("Given null product ID, when get product by ID, then return null")
    void givenNullProductId_WhenGetProductById_ThenReturnNull() {
        // Given
        Long nullId = null;

        // When
        Product product = getProductByIdCommand.getById(nullId);

        //Then
        assertNull(product);
    }
}