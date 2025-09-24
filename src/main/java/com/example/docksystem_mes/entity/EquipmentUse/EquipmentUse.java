package com.example.docksystem_mes.entity.EquipmentUse;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "equipment_use")
public class EquipmentUse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long equipUseNo;
    @Column(nullable = false)
    private Long equipNo;
    @Column(nullable = false)
    private String erpEquipNo;
    @Column(nullable = false)
    private String equipCode;
    @Column(nullable = false)
    private String equipName;
    @Column(nullable = false)
    private Long woNo;
}
