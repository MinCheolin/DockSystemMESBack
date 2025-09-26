package com.example.docksystem_mes.dto.WorkOrder;

import com.example.docksystem_mes.dto.Equipment.EquipmentResponseDto;
import com.example.docksystem_mes.dto.Material.MaterialResponseDto;
import com.example.docksystem_mes.entity.WorkOrder.WorkOrder;
import com.example.docksystem_mes.entity.WorkOrder.WorkOrderType;
import lombok.Data;

import java.util.Date;

@Data
public class WorkOrderResponseDto {
    private Long woNo;
    private String woName;
    private Date woStartDate;
    private Date woEndDate;
    private String woDetail;
    private String woDescription;
    private WorkOrderType type;
    private String typeLabel;
    private String ppNo;
    private EquipmentResponseDto equipment;
    private MaterialResponseDto material;

    public static WorkOrderResponseDto fromEntity(WorkOrder workOrder){
        WorkOrderResponseDto dto = new WorkOrderResponseDto();
        dto.setWoNo(workOrder.getWoNo());
        dto.setWoName(workOrder.getWoName());
        dto.setWoStartDate(workOrder.getWoStartDate());
        dto.setWoEndDate(workOrder.getWoEndDate());
        dto.setWoDetail(workOrder.getWoDetail());
        dto.setWoDescription(workOrder.getWoDescription());
        dto.setType(workOrder.getType());
        dto.setTypeLabel(workOrder.getType().getLabel());
        dto.setPpNo(workOrder.getPpNo());
        dto.setEquipment(EquipmentResponseDto.fromEntity(workOrder.getEquipment()));
        dto.setMaterial(MaterialResponseDto.fromEntity(workOrder.getMaterial()));
        return dto;
    }

}
