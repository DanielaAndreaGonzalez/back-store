package com.store.back.repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.store.back.models.tables.Sale;

public interface SaleRepository extends JpaRepository<Sale, Integer> {

    @Query("SELECT COUNT(s) FROM Sale s WHERE CAST(s.saleDate AS date) = :date")
    Long countBySaleDate(@Param("date") LocalDate date);

    @Query("SELECT SUM(s.total) FROM Sale s WHERE CAST(s.saleDate AS date) = :date")
    Double sumTotalBySaleDate(@Param("date") LocalDate date);

}
