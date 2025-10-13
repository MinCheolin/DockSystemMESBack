package com.example.docksystem_mes.entity.Process;

public enum ProcessStatus {
    MATERIAL("자재"),
    EQUIPMENT("장비");
    private final String label;

    ProcessStatus(String label){
        this.label=label;
    }
    public String getLabel(){
        return label;
    }

}

