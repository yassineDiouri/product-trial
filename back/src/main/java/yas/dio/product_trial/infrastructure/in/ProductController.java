package yas.dio.product_trial.infrastructure.in;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yas.dio.product_trial.domain.model.Product;
import yas.dio.product_trial.domain.ports.in.CreateProductCommand;
import yas.dio.product_trial.infrastructure.in.dto.CreateProductRequest;
import yas.dio.product_trial.infrastructure.in.exceptions.ProductValidationException;
import yas.dio.product_trial.infrastructure.in.mappers.ProductDtoMapper;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final CreateProductCommand createProduct;
    private final ProductDtoMapper productDtoMapper;

    @PostMapping
    public Product create(@RequestBody @Validated CreateProductRequest createProductRequest, Errors errors) {
        if (errors.hasErrors()) {
            throw new ProductValidationException(errors);
        }
        return createProduct.create(productDtoMapper.toModel(createProductRequest));
    }
}
