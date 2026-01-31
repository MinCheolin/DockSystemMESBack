package com.example.docksystem_mes.dto.Equipment;

import com.example.docksystem_mes.entity.Equipment.EquipmentType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class ToErpEquipmentDto {
    @JsonProperty("equipNo")
    private String erpEquipNo;
    @Enumerated(EnumType.STRING)
    private EquipmentType type;
}
