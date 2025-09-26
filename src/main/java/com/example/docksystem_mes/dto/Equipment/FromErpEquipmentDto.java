package com.example.docksystem_mes.dto.Equipment;

import com.example.docksystem_mes.entity.Equipment.Equipment;
import com.example.docksystem_mes.entity.Equipment.EquipmentType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class FromErpEquipmentDto {
    @JsonProperty("equipNo")
    private String erpEquipNo;
    @JsonProperty("equipCode")
    private String equipCode;
    @JsonProperty("equipName")
    private String equipName;
    @Enumerated(EnumType.STRING)
    private EquipmentType type;

    public static FromErpEquipmentDto fromEntity(Equipment equipment){
        FromErpEquipmentDto dto = new FromErpEquipmentDto();
        dto.setErpEquipNo(equipment.getErpEquipNo());
        dto.setEquipCode(equipment.getEquipCode());
        dto.setEquipName(equipment.getEquipName());
        dto.setType(equipment.getType());
        return dto;
    }
}
