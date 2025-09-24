package com.example.docksystem_mes.dto.WorkOrder;

import com.example.docksystem_mes.dto.Equipment.EquipmentDto;
import com.example.docksystem_mes.dto.Material.MaterialDto;
import com.example.docksystem_mes.entity.Equipment.Equipment;
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
    private EquipmentDto equipment;
    private MaterialDto material;

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
        dto.setEquipment(EquipmentDto.fromEntity(workOrder.getEquipment()));
        dto.setMaterial(MaterialDto.fromEntity(workOrder.getMaterial()));
        return dto;
    }

}
