package yas.dio.product_trial.domain.ports.in;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import yas.dio.product_trial.domain.model.Product;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CreateProductTest {

    private CreateProductCommand command;

    @Nested
    class GivenValidProduct {
        @Test
        void whenCreateProduct_ThenReturnProduct() {
            //Given
            Product product = new Product();
            //When
            command.create(product);
            //Then
            assertNotNull(product);
        }
    }
}