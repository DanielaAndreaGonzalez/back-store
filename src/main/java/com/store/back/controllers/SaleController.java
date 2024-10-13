package com.store.back.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.back.models.SaleDto;
import com.store.back.services.SaleService;

@RestController
@RequestMapping("/api/sales")
@CrossOrigin(origins = "*")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping("/all")
    public List<SaleDto> getAllSales() {
        return saleService.getAllSales();
    }

    @PostMapping("/create")
    public SaleDto createSale(@RequestBody SaleDto saleDto) {
        return saleService.createSale(saleDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSale(@PathVariable Integer id) {
        saleService.deleteSale(id);
    }
}
