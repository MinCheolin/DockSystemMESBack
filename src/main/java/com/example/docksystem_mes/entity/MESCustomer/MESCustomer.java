package com.example.docksystem_mes.entity.MESCustomer;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "mes_customers")
public class MESCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mesCustomerNo;
    @Column(nullable = false)
    private String erpCustomerNo;
    private String mesCustomerName;
}
