package com.store.back.services.Utilities;

import java.util.List;

import com.store.back.models.SaleItem;
import com.store.back.models.SaleItemDto;
import com.store.back.repositories.ProductRepository;

public class SaleItemConverter {

    public static SaleItemDto entityToDto(SaleItem saleItem) {
        return new SaleItemDto(
                saleItem.getId(),
                ProductConverter.entityToDto(saleItem.getProduct()),
                saleItem.getSale().getId(),
                saleItem.getQuantity());
    }

    public static List<SaleItemDto> entityesToDtos(List<SaleItem> saleItems) {
        return saleItems.stream()
                .map(SaleItemConverter::entityToDto)
                .toList();
    }

    public static SaleItem dtoToEntity(SaleItemDto saleItemDto, ProductRepository productRepository) {
        /*
         * return new SaleItem(
         * saleItemDto.id(),
         * saleRepository.findById(saleItemDto.saleId()).orElseThrow(() -> new
         * RuntimeException("Sale not found")),
         * productRepository.findById(saleItemDto.product().id())
         * .orElseThrow(() -> new RuntimeException("Product not found")),
         * saleItemDto.quantity());
         */
        SaleItem saleItem = new SaleItem();
        saleItem.setQuantity(saleItemDto.quantity());
        saleItem.setProduct(productRepository.findById(saleItemDto.product().id())
                .orElseThrow(() -> new RuntimeException("Product not found")));
        return saleItem;
    }

    public static List<SaleItem> dtosToEntityes(List<SaleItemDto> saleItemDtos, ProductRepository productRepository) {
        return saleItemDtos.stream()
                .map(saleItemDto -> SaleItemConverter.dtoToEntity(saleItemDto, productRepository))
                .toList();
    }
}
