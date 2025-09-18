package yas.dio.product_trial.infrastructure.adapters.out.jpa.mappers;

public interface EntityMapper<M, E> {
    M toModel(E entity);

    E toEntity(M model);
}
