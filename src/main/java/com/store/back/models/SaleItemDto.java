package com.store.back.models;

public record SaleItemDto(Integer id, ProductDto product, Integer saleId, Integer quantity) {

}
