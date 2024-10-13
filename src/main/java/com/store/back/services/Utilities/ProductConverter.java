package com.store.back.services.Utilities;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.store.back.models.Product;
import com.store.back.models.ProductDto;

public class ProductConverter {

    public static ProductDto entityToDto(Product product) {
        return Optional.ofNullable(product)
                .map(prod -> new ProductDto(prod.getId(), prod.getPrice(), prod.getName(), prod.getStock()))
                .orElseThrow(() -> new IllegalArgumentException("Product cannot be null"));
    }

    public static List<ProductDto> entitiesToDtos(List<Product> products) {
        return Optional.ofNullable(products)
                .stream()
                .flatMap(Collection::stream)
                .map(ProductConverter::entityToDto)
                .collect(Collectors.toList());
    }

    public static Product dtoToEntity(ProductDto productDto) {
        return Optional.ofNullable(productDto)
                .map(dto -> {
                    Product product = new Product();
                    product.setId(dto.id());
                    product.setPrice(dto.price());
                    product.setName(dto.name());
                    product.setStock(dto.stock());
                    return product;
                })
                .orElseThrow(() -> new IllegalArgumentException("ProductDto cannot be null"));
    }

    public static List<Product> dtosToEntities(List<ProductDto> productDtos) {
        return Optional.ofNullable(productDtos)
                .stream()
                .flatMap(Collection::stream)
                .map(ProductConverter::dtoToEntity)
                .collect(Collectors.toList());
    }
}
