package com.store.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.back.models.tables.Sale;

public interface SaleRepository extends JpaRepository<Sale, Integer> {

}
