package com.example.docksystem_mes.dto.QualityControl;

import com.example.docksystem_mes.dto.WorkOrder.WorkOrderResponseDto;
import com.example.docksystem_mes.entity.QualityControl.QualityControl;
import com.example.docksystem_mes.entity.QualityControl.QualityControlType;
import lombok.Data;

@Data
public class QualityControlResponseDto {
    private Long qcNo;
    private QualityControlType type;
    private WorkOrderResponseDto workOrder;

    public static QualityControlResponseDto fromEntity(QualityControl qualityControl){
        QualityControlResponseDto dto = new QualityControlResponseDto();
        dto.setQcNo(qualityControl.getQcNo());
        dto.setType(qualityControl.getType());
        dto.setWorkOrder(WorkOrderResponseDto.fromEntity(qualityControl.getWorkOrder()));
        return dto;
    }
}
