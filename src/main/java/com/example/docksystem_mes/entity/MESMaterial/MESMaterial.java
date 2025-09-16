package com.example.docksystem_mes.entity.MESMaterial;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "mes_material")
public class MESMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mesMaterialNo;
    @Column(nullable = false)
    private String erpMaterialNo;
    private String mesMaterialCode;
    private String mesMaterialName;
    private String mesMaterialType;

}
