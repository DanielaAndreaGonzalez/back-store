package com.store.back.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.store.back.models.DTO.ProductDto;
import com.store.back.models.DTO.ProductTopSelling;
import com.store.back.services.ProductService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public List<ProductDto> getAll() {
        return productService.getAll();
    }

    @PostMapping("/create")
    public ProductDto create(@RequestBody ProductDto productDto) {
        return productService.create(productDto);
    }

    @PutMapping("update/{id}")
    public ProductDto update(@PathVariable Integer id, @RequestBody ProductDto productDto) {
        return productService.update(id, productDto);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Integer id) {
        productService.delete(id);
    }

    @GetMapping("/low-inventory")
    public List<ProductDto> lowInventory() {
        try {
            return productService.lowInventoryProduct();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("/top-selling/{month}/{year}")
    public ProductTopSelling getProductTopSelling(@PathVariable Integer month, @PathVariable Integer year) {
        return productService.getProductoMasVendidoDelMes(month, year);
    }

}
