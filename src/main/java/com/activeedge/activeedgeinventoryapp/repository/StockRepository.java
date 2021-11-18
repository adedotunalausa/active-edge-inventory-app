package com.activeedge.activeedgeinventoryapp.repository;

import com.activeedge.activeedgeinventoryapp.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
}
