package com.example.docksystem_mes.dto.WorkOrder;

import com.example.docksystem_mes.entity.WorkOrder.WorkOrderType;
import lombok.Data;

import java.util.Date;

@Data
public class WorkOrderUpdateRequestDto {
    private Long woNo;
    private String woName;
    private Date woStartDate;
    private Date woEndDate;
    private String woDetail;
    private String woDescription;
    private WorkOrderType type;
    private String ppNo;
    private Long equipNo;
    private Long materialNo;
}
