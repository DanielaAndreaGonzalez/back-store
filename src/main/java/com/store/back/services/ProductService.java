package com.store.back.services;

import java.util.List;
import com.store.back.models.DTO.ProductDto;
import com.store.back.models.DTO.SaleItemDto;

public interface ProductService {
    List<ProductDto> getAll();

    ProductDto getById(Integer id);

    ProductDto create(ProductDto productDto);

    ProductDto update(Integer id, ProductDto productDto);

    void delete(Integer id);

    void decreaseStock(List<SaleItemDto> salesImtemDtos);
}
