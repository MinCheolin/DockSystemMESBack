package com.example.docksystem_mes.dto.WorkOrder;

import com.example.docksystem_mes.dto.Material.MaterialDto;
import com.example.docksystem_mes.dto.Process.ProcessResponseDto;
import com.example.docksystem_mes.entity.WorkOrder.WorkOrder;
import com.example.docksystem_mes.entity.WorkOrder.WorkOrderType;
import lombok.Data;

import java.util.Date;

@Data
public class WorkOrderResponseDto {
    private Long woNo;
    private Date woStartDate;
    private Date woEndDate;
    private String woDetail;
    private String woDescription;
    private WorkOrderType type;
    private String typeLabel;
    private ProcessResponseDto process;
    private MaterialDto material;

    public static WorkOrderResponseDto fromEntity(WorkOrder workOrder){
        WorkOrderResponseDto dto = new WorkOrderResponseDto();
        dto.setWoNo(workOrder.getWoNo());
        dto.setWoStartDate(workOrder.getWoStartDate());
        dto.setWoEndDate(workOrder.getWoEndDate());
        dto.setWoDetail(workOrder.getWoDetail());
        dto.setWoDescription(workOrder.getWoDescription());
        dto.setType(workOrder.getType());
        dto.setTypeLabel(workOrder.getType().getLabel());
        dto.setProcess(ProcessResponseDto.fromEntity(workOrder.getMesProcess()));
        dto.setMaterial(MaterialDto.fromEntity(workOrder.getMaterial()));
        return dto;
    }

}
