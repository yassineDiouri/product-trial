package yas.dio.product_trial.domain.ports.in;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;
import yas.dio.product_trial.application.ports.in.CreateProductCommand;
import yas.dio.product_trial.domain.model.InventoryStatus;
import yas.dio.product_trial.domain.model.Product;
import yas.dio.product_trial.infrastructure.out.jpa.ProductJpaRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
@Transactional
@DisplayName("CreateProductCommand Tests")
class CreateProductCommandTest {

    @Autowired
    private CreateProductCommand command;
    @Autowired
    private ProductJpaRepository productJpaRepository;

    @Nested
    class GivenValidProduct {
        @Test
        void whenCreateProduct_ThenReturnProduct() {
            //Given
            Product product = new Product();
            product.setCode("code");
            product.setName("name");
            product.setCategory("category");
            product.setPrice(0.0);
            product.setQuantity(0);
            product.setInternalReference("internalReference");
            product.setInventoryStatus(InventoryStatus.INSTOCK);
            //When
            product = command.create(product);
            //Then
            assertNotNull(product);
            assertNotNull(product.getId());
            assertTrue(productJpaRepository.findById(product.getId()).isPresent());
        }
    }
}