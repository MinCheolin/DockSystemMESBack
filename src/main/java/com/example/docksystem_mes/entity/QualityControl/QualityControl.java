package com.example.docksystem_mes.entity.QualityControl;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "quality_control")
public class QualityControl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qcNo;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private QualityControlType type;

}
