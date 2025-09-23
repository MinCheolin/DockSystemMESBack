package com.example.docksystem_mes.entity.QualityControl;

import com.example.docksystem_mes.entity.WorkOrder.WorkOrder;
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
    @OneToOne
    @JoinColumn(name = "wo_no")
    private WorkOrder workOrder;
}
