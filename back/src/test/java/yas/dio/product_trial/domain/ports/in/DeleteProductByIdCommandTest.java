package yas.dio.product_trial.domain.ports.in;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;
import yas.dio.product_trial.application.ports.in.DeleteProductByIdCommand;
import yas.dio.product_trial.application.ports.out.ProductRepository;
import yas.dio.product_trial.domain.model.InventoryStatus;
import yas.dio.product_trial.domain.model.Product;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
@Transactional
@DisplayName("DeleteProductByIdCommand Tests")
class DeleteProductByIdCommandTest {

    @Autowired
    private DeleteProductByIdCommand deleteProductByIdCommand;
    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("Given existing product ID, when delete product, then product is removed from database")
    void givenExistingProductId_WhenDeleteProduct_ThenProductIsRemovedFromDatabase() {
        // Given
        Product product = Product.builder()
                .code("PROD001")
                .name("Product to Delete")
                .description("This product will be deleted")
                .image("delete-image.jpg")
                .category("Electronics")
                .price(99.99)
                .quantity(10)
                .internalReference("REF001")
                .shellId(1L)
                .inventoryStatus(InventoryStatus.INSTOCK)
                .rating(4.0)
                .build();

        Product savedProduct = productRepository.create(product);
        Long productId = savedProduct.getId();

        // Vérification que le produit existe bien avant suppression
        assertThat(productRepository.findById(productId)).isNotNull();

        // When
        deleteProductByIdCommand.delete(productId);

        // Then
        assertThat(productRepository.findById(productId)).isEmpty();
    }
}