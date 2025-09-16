package com.example.docksystem_mes.entity.Process;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ProcessType {
    READY("대기"),
    PROGRESS("진행"),
    COMPLETE("완료");

    private final String label;

    ProcessType(String label){
        this.label=label;
    }
    @JsonValue // READY로 응답받지 않고 대기로 응답받으려면 필요함
    public String getLabel(){
        return label;
    }
}
