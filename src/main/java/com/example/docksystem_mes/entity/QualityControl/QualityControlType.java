package com.example.docksystem_mes.entity.QualityControl;

import com.fasterxml.jackson.annotation.JsonValue;

public enum QualityControlType {
    WAITING("대기"),
    COMPLETE("완료");

    private final String label;

    QualityControlType(String label){
        this.label = label;
    }

    @JsonValue
    public String getLabel(){
        return label;
    }
}
