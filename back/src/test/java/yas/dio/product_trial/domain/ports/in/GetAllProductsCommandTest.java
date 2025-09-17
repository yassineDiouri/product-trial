package yas.dio.product_trial.domain.ports.in;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;
import yas.dio.product_trial.application.ports.in.GetAllProductsCommand;
import yas.dio.product_trial.application.ports.out.ProductRepository;
import yas.dio.product_trial.domain.model.InventoryStatus;
import yas.dio.product_trial.domain.model.Product;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
@Transactional
@DisplayName("GetAllProductsCommand Tests")
class GetAllProductsCommandTest {

    @Autowired
    private GetAllProductsCommand getAllProductsCommand;
    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("Given empty database, when get all products, then return empty list")
    void givenEmptyDatabase_WhenGetAllProducts_ThenReturnEmptyList() {
        // Given

        // When
        List<Product> products = getAllProductsCommand.getAll();

        // Then
        assertThat(products).isNotNull();
        assertThat(products).isEmpty();
    }

    @Test
    @DisplayName("Given single product in database, when get all products, then return list with one product")
    void givenSingleProduct_WhenGetAllProducts_ThenReturnListWithOneProduct() {
        // Given
        Product product = Product.builder()
                .code("PROD001")
                .name("Single Product")
                .description("Single product description")
                .image("single-image.jpg")
                .category("Electronics")
                .price(99.99)
                .quantity(10)
                .internalReference("REF001")
                .shellId(1L)
                .inventoryStatus(InventoryStatus.INSTOCK)
                .rating(4.5)
                .build();

        Product savedProduct = productRepository.create(product);

        // When
        List<Product> products = getAllProductsCommand.getAll();

        // Then
        assertThat(products).isNotNull();
        assertThat(products).hasSize(1);

        Product retrievedProduct = products.get(0);
        assertThat(retrievedProduct.getId()).isEqualTo(savedProduct.getId());
        assertThat(retrievedProduct.getCode()).isEqualTo("PROD001");
        assertThat(retrievedProduct.getName()).isEqualTo("Single Product");
        assertThat(retrievedProduct.getPrice()).isEqualTo(99.99);
    }

    @Test
    @DisplayName("Given multiple products in database, when get all products, then return complete list")
    void givenMultipleProducts_WhenGetAllProducts_ThenReturnCompleteList() {
        // Given
        Product product1 = Product.builder()
                .code("PROD001")
                .name("Product One")
                .description("First product")
                .category("Electronics")
                .internalReference("REF001")
                .price(100.0)
                .quantity(5)
                .inventoryStatus(InventoryStatus.INSTOCK)
                .rating(4.0)
                .build();

        Product product2 = Product.builder()
                .code("PROD002")
                .name("Product Two")
                .description("Second product")
                .category("Home & Garden")
                .internalReference("REF002")
                .price(200.0)
                .quantity(3)
                .inventoryStatus(InventoryStatus.LOWSTOCK)
                .rating(3.5)
                .build();

        Product product3 = Product.builder()
                .code("PROD003")
                .name("Product Three")
                .description("Third product")
                .category("Sports")
                .internalReference("REF003")
                .price(300.0)
                .quantity(0)
                .inventoryStatus(InventoryStatus.OUTOFSTOCK)
                .rating(5.0)
                .build();

        Product savedProduct1 = productRepository.create(product1);
        Product savedProduct2 = productRepository.create(product2);
        Product savedProduct3 = productRepository.create(product3);

        // When
        List<Product> products = getAllProductsCommand.getAll();

        // Then
        assertThat(products).isNotNull();
        assertThat(products).hasSize(3);

        List<Long> retrievedIds = products.stream()
                .map(Product::getId)
                .toList();
        assertThat(retrievedIds).containsExactlyInAnyOrder(
                savedProduct1.getId(),
                savedProduct2.getId(),
                savedProduct3.getId()
        );

        List<String> retrievedCodes = products.stream()
                .map(Product::getCode)
                .toList();
        assertThat(retrievedCodes).containsExactlyInAnyOrder(
                "PROD001", "PROD002", "PROD003"
        );
    }
}