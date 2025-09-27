package com.example.docksystem_mes.dto.Equipment;

import com.example.docksystem_mes.entity.Equipment.Equipment;
import com.example.docksystem_mes.entity.Equipment.EquipmentType;
import lombok.Data;

@Data
public class EquipmentResponseDto {
    private Long equipNo;
    private String equipCode;
    private String equipName;
    private EquipmentType type;

    public static EquipmentResponseDto fromEntity(Equipment equipment){
        EquipmentResponseDto dto = new EquipmentResponseDto();
        dto.setEquipNo(equipment.getEquipNo());
        dto.setEquipCode(equipment.getEquipCode());
        dto.setEquipName(equipment.getEquipName());
        dto.setType(equipment.getType());
        return dto;
    }
}
