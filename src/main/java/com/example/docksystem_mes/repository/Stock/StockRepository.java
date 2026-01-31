package com.example.docksystem_mes.repository.Stock;

import com.example.docksystem_mes.entity.Stock.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock,Long> {
}
