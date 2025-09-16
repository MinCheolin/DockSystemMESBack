package com.example.docksystem_mes.entity.MESEquipment;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "mes_equipments")
public class MESEquipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mesEquipNo;
    @Column(nullable = false)
    private String erpEquipNo;
    private String mesEquipName;
    @Enumerated(EnumType.STRING)
    private MESEquipmentType type;
}
