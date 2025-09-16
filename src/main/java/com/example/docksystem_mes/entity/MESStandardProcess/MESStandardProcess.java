package com.example.docksystem_mes.entity.MESStandardProcess;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "mes_standard_processes")
public class MESStandardProcess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mesSpNo;
    @Column(nullable = false)
    private String erpSpNo;
    private String mesSpCode;
    private String mesSpName;
    private String mesSpTime;
    private String mesSpEquipment;
}
