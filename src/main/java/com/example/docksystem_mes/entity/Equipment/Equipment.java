package com.example.docksystem_mes.entity.Equipment;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "equipments")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long equipNo;
    @Column(nullable = false)
    private String erpEquipNo;
    private String equipCode;
    private String equipName;

}
