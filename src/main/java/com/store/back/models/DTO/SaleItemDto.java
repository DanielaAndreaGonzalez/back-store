package com.store.back.models.DTO;

public record SaleItemDto(Integer id, ProductDto product, Integer saleId, Integer quantity) {

}
