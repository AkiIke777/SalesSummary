package com.example.salessummary.repositories;

import com.example.salessummary.entities.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SalesRepository extends JpaRepository<Sales,Long> {
    List<Sales> findSalesById(long kw);
}