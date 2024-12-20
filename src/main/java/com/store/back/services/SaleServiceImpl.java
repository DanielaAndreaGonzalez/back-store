package com.store.back.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.back.models.DTO.SaleDto;
import com.store.back.models.tables.Sale;
import com.store.back.models.tables.SaleItem;
import com.store.back.repositories.ProductRepository;
import com.store.back.repositories.SaleItemRepository;
import com.store.back.repositories.SaleRepository;
import com.store.back.services.Utilities.SaleConverter;
import com.store.back.services.Utilities.SaleItemConverter;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleItemRepository saleItemRepository;

    @Autowired
    private ProductService productService;

    @Override
    public SaleDto createSale(SaleDto saleDto) {
        Sale sale = SaleConverter.dtoToEntity(saleDto);
        sale = saleRepository.save(sale);

        // Establece la referencia a la venta en cada `SaleItem`
        List<SaleItem> saleItems = SaleItemConverter.dtosToEntityes(saleDto.saleItems(), productRepository);
        for (SaleItem item : saleItems) {
            item.setSale(sale);
        }

        // Guarda cada `SaleItem` en el repositorio correspondiente
        saleItemRepository.saveAll(saleItems);

        // Disminuye el stock del producto vendido
        productService.decreaseStock(saleDto.saleItems());

        return SaleConverter.entityToDto(sale);
    }

    @Override
    public SaleDto getSaleById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSaleById'");
    }

    @Override
    public SaleDto updateSale(Integer id, SaleDto saleDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateSale'");
    }

    @Override
    public void deleteSale(Integer id) {
        saleRepository.deleteById(id);
    }

    @Override
    public List<SaleDto> getAllSales() {
        List<Sale> sales = saleRepository.findAll();
        return SaleConverter.entitysToDtos(sales);
    }

    @Override
    public Long countBySaleDate(LocalDate date) {
        try {
            return saleRepository.countBySaleDate(date);
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }

    }

    @Override
    public Double totalBySaleDate(LocalDate fecha) {
        try {
            return saleRepository.sumTotalBySaleDate(fecha);
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }

}
