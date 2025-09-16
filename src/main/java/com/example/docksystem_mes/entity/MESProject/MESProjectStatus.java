package com.example.docksystem_mes.entity.MESProject;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MESProjectStatus {
    Waiting("대기"),
    Processing("진행"),
    Complete("완료");

    private final String label;

    MESProjectStatus(String label){
        this.label=label;
    }
    @JsonValue // READY로 응답받지 않고 대기로 응답받으려면 필요함
    public String getLabel(){
        return label;
    }
}
