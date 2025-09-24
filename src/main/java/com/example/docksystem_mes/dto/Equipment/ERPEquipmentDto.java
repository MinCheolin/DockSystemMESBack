package com.example.docksystem_mes.dto.Equipment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ERPEquipmentDto {
    @JsonProperty("equipNo")
    private String erpEquipNo;
}
