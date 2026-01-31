package com.example.docksystem_mes.entity.QualityControl;

import com.example.docksystem_mes.dto.QualityControl.QualityControlUpdateRequestDto;
import com.example.docksystem_mes.entity.Stock.Stock;
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
    private int totalQuantity;
    private int successQuantity;
    private int faultQuantity;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private QualityControlType type;
    @OneToOne
    @JoinColumn(name = "wo_no")
    private WorkOrder workOrder;
    @ManyToOne
    @JoinColumn(name = "stock_no")
    private Stock stock;

    public void InspectionCompleted(QualityControlUpdateRequestDto dto){
        this.successQuantity = dto.getSuccessQuantity();
        this.faultQuantity = dto.getFaultQuantity();
        this.type = dto.getType();
    }


}
