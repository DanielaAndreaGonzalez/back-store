package com.store.back.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.back.models.Product;
import com.store.back.models.ProductDto;
import com.store.back.repositories.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDto> getAll() {
        return getProductDtosFromProduct(productRepository.findAll());
    }

    @Override
    public ProductDto getById(Integer id) {
        return getProductDtoFromProduct(productRepository.findById(id).orElse(null));
    }

    @Override
    public ProductDto create(ProductDto productDto) {
        return getProductDtoFromProduct(productRepository.save(getProductFromProductDto(productDto)));
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

    private List<ProductDto> getProductDtosFromProduct(List<Product> products) {
        return Optional.ofNullable(products)
                .stream()
                .flatMap(Collection::stream)
                .map(this::getProductDtoFromProduct)
                .collect(Collectors.toList());
    }

    private ProductDto getProductDtoFromProduct(Product product) {
        return Optional.ofNullable(product)
                .map(prod -> new ProductDto(prod.getId(), prod.getPrice(), prod.getName(), prod.getStock()))
                .orElseThrow(() -> new IllegalArgumentException("Product cannot be null"));
    }

    private Product getProductFromProductDto(ProductDto productDto) {
        return Optional.ofNullable(productDto)
                .map(prodDto -> new Product(prodDto.id(), prodDto.price(), prodDto.name(), prodDto.stock()))
                .orElseThrow(() -> new IllegalArgumentException("ProductDto cannot be null"));
    }

}
