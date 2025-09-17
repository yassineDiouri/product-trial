package yas.dio.product_trial.infrastructure.in;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yas.dio.product_trial.application.ports.in.CreateProductCommand;
import yas.dio.product_trial.application.ports.in.GetAllProductsCommand;
import yas.dio.product_trial.application.ports.in.GetProductByIdCommand;
import yas.dio.product_trial.application.ports.in.UpdateProductCommand;
import yas.dio.product_trial.infrastructure.in.dto.requests.CreateProductRequest;
import yas.dio.product_trial.infrastructure.in.dto.requests.UpdateProductRequest;
import yas.dio.product_trial.infrastructure.in.dto.responses.ProductResponse;
import yas.dio.product_trial.infrastructure.in.exceptions.ProductValidationException;
import yas.dio.product_trial.infrastructure.in.mappers.ProductMapper;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final GetProductByIdCommand getProductByIdCommand;
    private final CreateProductCommand createProductCommand;
    private final UpdateProductCommand updateProductCommand;
    private final GetAllProductsCommand getAllProductsCommand;
    private final ProductMapper productMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductResponse> getAll() {
        return getAllProductsCommand.getAll().stream().map(productMapper::toResponse).toList();
    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductResponse getById(@PathVariable Long id) {
        return productMapper.toResponse(getProductByIdCommand.getById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductResponse create(@RequestBody @Validated CreateProductRequest createProductRequest, Errors errors) {
        if (errors.hasErrors()) {
            throw new ProductValidationException(errors);
        }
        return productMapper.toResponse(
                createProductCommand.create(
                        productMapper.toModel(createProductRequest)));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody @Validated UpdateProductRequest updateProductRequest, Errors errors) {
        if (errors.hasErrors()) {
            throw new ProductValidationException(errors);
        }
        updateProductCommand.update(productMapper.toModel(updateProductRequest));
    }
}
