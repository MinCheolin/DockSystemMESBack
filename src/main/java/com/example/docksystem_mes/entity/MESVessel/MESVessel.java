package com.example.docksystem_mes.entity.MESVessel;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "mes_vessel")
public class MESVessel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mesVesselNo;
    @Column(nullable = false)
    private String erpVesselNo;
    private String mesVesselName;
    private String mesVesselType;
}
