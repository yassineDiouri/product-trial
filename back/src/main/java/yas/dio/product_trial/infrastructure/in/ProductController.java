package yas.dio.product_trial.infrastructure.in;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yas.dio.product_trial.application.ports.in.CreateProductCommand;
import yas.dio.product_trial.application.ports.in.UpdateProductCommand;
import yas.dio.product_trial.domain.model.Product;
import yas.dio.product_trial.infrastructure.in.dto.request.CreateProductRequest;
import yas.dio.product_trial.infrastructure.in.dto.request.UpdateProductRequest;
import yas.dio.product_trial.infrastructure.in.exceptions.ProductValidationException;
import yas.dio.product_trial.infrastructure.in.mappers.ProductRequestMapper;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final CreateProductCommand createProductCommand;
    private final UpdateProductCommand updateProductCommand;
    private final ProductRequestMapper productRequestMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Product create(@RequestBody @Validated CreateProductRequest createProductRequest, Errors errors) {
        if (errors.hasErrors()) {
            throw new ProductValidationException(errors);
        }
        return createProductCommand.create(productRequestMapper.toModel(createProductRequest));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody @Validated UpdateProductRequest updateProductRequest, Errors errors) {
        if (errors.hasErrors()) {
            throw new ProductValidationException(errors);
        }
        updateProductCommand.update(productRequestMapper.toModel(updateProductRequest));
    }
}
