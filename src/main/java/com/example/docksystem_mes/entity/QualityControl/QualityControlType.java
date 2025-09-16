package com.example.docksystem_mes.entity.QualityControl;

import com.fasterxml.jackson.annotation.JsonValue;

public enum QualityControlType {
    ERROR("불량"),
    NORMAL("정상");

    private final String label;

    QualityControlType(String label){
        this.label = label;
    }

    @JsonValue
    public String getLabel(){
        return label;
    }
}
