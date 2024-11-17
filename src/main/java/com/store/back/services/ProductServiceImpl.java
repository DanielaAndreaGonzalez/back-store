package com.store.back.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.back.models.tables.Product;
import com.store.back.models.DTO.ProductDto;
import com.store.back.models.DTO.ProductTopSelling;
import com.store.back.models.DTO.SaleItemDto;
import com.store.back.repositories.ProductRepository;
import com.store.back.repositories.SaleItemRepository;
import com.store.back.services.Utilities.ProductConverter;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleItemRepository saleItemRepository;

    private static final Integer INVENTARIO_BAJO_UMBRAL = 10;

    @Override
    public List<ProductDto> getAll() {
        return ProductConverter.entitiesToDtos(productRepository.findAll());
    }

    @Override
    public ProductDto getById(Integer id) {
        return ProductConverter.entityToDto(productRepository.findById(id).orElse(null));
    }

    @Override
    public ProductDto create(ProductDto productDto) {
        return ProductConverter.entityToDto(
                productRepository.save(ProductConverter.dtoToEntity(productDto)));
    }

    @Override
    public ProductDto update(Integer id, ProductDto productDto) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setPrice(productDto.price());
                    product.setName(productDto.name());
                    product.setStock(productDto.stock());
                    return product;
                })
                .map(productRepository::save)
                .map(product -> new ProductDto(product.getId(), product.getPrice(), product.getName(),
                        product.getStock()))
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    public void decreaseStock(List<SaleItemDto> salesDetailsDto) {
        salesDetailsDto.forEach(dto -> {
            Product product = productRepository.findById(dto.product().id())
                    .orElseThrow(
                            () -> new EntityNotFoundException("Product not found with id: " + dto.product().id()));

            if (product.getStock() < dto.quantity()) {
                throw new IllegalArgumentException("Insufficient stock for product " + product.getName());
            }

            product.setStock(product.getStock() - dto.quantity());
            productRepository.save(product);
        });
    }

    @Override
    public List<ProductDto> lowInventoryProduct() {
        try {
            return ProductConverter.entitiesToDtos(productRepository.findByStockLessThan(INVENTARIO_BAJO_UMBRAL));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ProductTopSelling getProductoMasVendidoDelMes(int month, int year) {
        List<Object[]> resultados = saleItemRepository.findTopSellingProducts(month, year);

        if (!resultados.isEmpty()) {
            Product productoMasVendido = (Product) resultados.get(0)[0];
            Integer cantidadVendida = ((Long) resultados.get(0)[1]).intValue();
            ProductTopSelling productTopSelling = new ProductTopSelling(
                    ProductConverter.entityToDto(productoMasVendido), cantidadVendida);
            return productTopSelling;
        }
        return null;
    }

}
