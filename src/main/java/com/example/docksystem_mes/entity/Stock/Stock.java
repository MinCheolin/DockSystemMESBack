package com.example.docksystem_mes.entity.Stock;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockNo;
    @Column(nullable = false)
    private String stockName;
    @Column(nullable = false,columnDefinition = "int default 0")
    private int normalCount;
    @Column(nullable = false,columnDefinition = "int default 0")
    private int errorCount;
}
