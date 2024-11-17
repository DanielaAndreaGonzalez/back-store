package com.store.back.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.store.back.models.tables.SaleItem;

public interface SaleItemRepository extends JpaRepository<SaleItem, Integer> {
    @Query("SELECT si.product, SUM(si.quantity) as totalVendida " +
            "FROM SaleItem si " +
            "WHERE MONTH(si.sale.saleDate) = :month AND YEAR(si.sale.saleDate) = :year " +
            "GROUP BY si.product " +
            "ORDER BY totalVendida DESC")
    List<Object[]> findTopSellingProducts(@Param("month") int month, @Param("year") int year);
}
