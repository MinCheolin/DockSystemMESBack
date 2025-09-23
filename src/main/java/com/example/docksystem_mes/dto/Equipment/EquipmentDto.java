package com.example.docksystem_mes.dto.Equipment;

import com.example.docksystem_mes.entity.Equipment.Equipment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EquipmentDto {
    @JsonProperty("equipNo")
    private String erpEquipNo;
    @JsonProperty("equipCode")
    private String equipCode;
    @JsonProperty("equipName")
    private String equipName;

    public static EquipmentDto fromEntity(Equipment equipment){
        EquipmentDto dto = new EquipmentDto();
        dto.setErpEquipNo(equipment.getErpEquipNo());
        dto.setEquipCode(equipment.getEquipCode());
        dto.setEquipName(equipment.getEquipName());
        return dto;
    }
}
