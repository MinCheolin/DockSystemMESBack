package com.example.docksystem_mes.dto.QualityControl;

import com.example.docksystem_mes.dto.Stock.StockResponseDto;
import com.example.docksystem_mes.dto.WorkOrder.WorkOrderResponseDto;
import com.example.docksystem_mes.entity.QualityControl.QualityControl;
import com.example.docksystem_mes.entity.QualityControl.QualityControlType;
import com.example.docksystem_mes.entity.Stock.Stock;
import com.example.docksystem_mes.entity.WorkOrder.WorkOrder;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class QualityControlResponseDto {
    private Long qcNo;
    private int totalQuantity;
    private int successQuantity;
    private int faultQuantity;
    private QualityControlType type;
    private StockResponseDto stock;
    private WorkOrderResponseDto workOrder;

    public static QualityControlResponseDto fromEntity(QualityControl qualityControl){
        QualityControlResponseDto dto = new QualityControlResponseDto();
        dto.setQcNo(qualityControl.getQcNo());
        dto.setTotalQuantity(qualityControl.getTotalQuantity());
        dto.setSuccessQuantity(qualityControl.getSuccessQuantity());
        dto.setFaultQuantity(qualityControl.getFaultQuantity());
        dto.setType(qualityControl.getType());
        dto.setStock(StockResponseDto.fromEntity(qualityControl.getStock()));
        dto.setWorkOrder(WorkOrderResponseDto.fromEntity(qualityControl.getWorkOrder()));
        return dto;
    }
}
