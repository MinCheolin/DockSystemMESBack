package com.example.docksystem_mes.dto.WorkOrder;

import com.example.docksystem_mes.entity.WorkOrder.WorkOrderType;
import lombok.Data;

import java.util.Date;

@Data
public class WorkOrderUpdateRequestDto {
    private Date woStartDate;
    private Date woEndDate;
    private String woDetail;
    private String woDescription;
    private WorkOrderType type;
    private Long processNo;
    private Long materialNo;
}
