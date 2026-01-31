package com.example.docksystem_mes.entity.WorkOrder;

import com.fasterxml.jackson.annotation.JsonValue;

public enum WorkOrderType {
    READY("대기"),
    PROGRESS("진행"),
    QUALITY("품질"),
    COMPLETE("완료");

    private final String label;

    WorkOrderType(String label){
        this.label = label;
    }

    @JsonValue
    public String getLabel(){
        return label;
    }
}
