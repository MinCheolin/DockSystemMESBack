package com.example.docksystem_mes.entity.MESEquipment;


import com.fasterxml.jackson.annotation.JsonValue;

public enum MESEquipmentType {
    Operating("가동"),
    NotOperating("비가동"),
    Repair("수리"),
    Disposed("폐기");

    private final String label;

    MESEquipmentType(String label){
        this.label=label;
    }
    @JsonValue // READY로 응답받지 않고 대기로 응답받으려면 필요함
    public String getLabel(){
        return label;
    }
}
