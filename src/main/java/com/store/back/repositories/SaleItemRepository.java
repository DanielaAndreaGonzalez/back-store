package com.store.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.back.models.tables.SaleItem;

public interface SaleItemRepository extends JpaRepository<SaleItem, Integer> {

}
