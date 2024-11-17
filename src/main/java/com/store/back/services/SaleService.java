package com.store.back.services;

import java.time.LocalDate;
import java.util.List;

import com.store.back.models.DTO.SaleDto;

public interface SaleService {
    SaleDto createSale(SaleDto saleDto);

    SaleDto getSaleById(Integer id);

    SaleDto updateSale(Integer id, SaleDto saleDto);

    void deleteSale(Integer id);

    List<SaleDto> getAllSales();

    Long countBySaleDate(LocalDate fecha);

    Double totalBySaleDate(LocalDate fecha);
}
