package com.store.back.services.Utilities;

import java.util.List;

import com.store.back.models.DTO.SaleDto;
import com.store.back.models.tables.Sale;

public class SaleConverter {

    public static SaleDto entityToDto(Sale sale) {
        return new SaleDto(sale.getId(), sale.getSaleDate(), sale.getQuantity(), sale.getTotal(),
                SaleItemConverter.entityesToDtos(sale.getSaleItems()));
    }

    public static List<SaleDto> entitysToDtos(List<Sale> sales) {
        return sales.stream().map(SaleConverter::entityToDto).toList();
    }

    public static Sale dtoToEntity(SaleDto saleDto) {
        Sale sale = new Sale();
        sale.setSaleDate(saleDto.saleDate());
        sale.setQuantity(saleDto.quantity());
        sale.setTotal(saleDto.total());
        return sale;
    }

    public static List<Sale> dtosToEntityes(List<SaleDto> salesDto) {
        return salesDto.stream().map(saleDto -> SaleConverter.dtoToEntity(saleDto)).toList();
    }
}
