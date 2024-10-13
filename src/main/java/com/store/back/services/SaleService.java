package com.store.back.services;

import java.util.List;

import com.store.back.models.SaleDto;

public interface SaleService {
    SaleDto createSale(SaleDto saleDto);

    SaleDto getSaleById(Integer id);

    SaleDto updateSale(Integer id, SaleDto saleDto);

    void deleteSale(Integer id);

    List<SaleDto> getAllSales();
}
