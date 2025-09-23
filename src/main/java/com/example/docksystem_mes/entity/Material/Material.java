package com.example.docksystem_mes.entity.Material;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "materials")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long materialNo;
    @Column(nullable = false)
    private String erpMaterialNo;
    private String materialCode;
    private String materialName;
}
