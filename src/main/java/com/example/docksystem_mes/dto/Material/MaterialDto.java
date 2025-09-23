package com.example.docksystem_mes.dto.Material;

import com.example.docksystem_mes.entity.Material.Material;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MaterialDto {
    @JsonProperty("materialNo")
    private String erpMaterialNo;
    @JsonProperty("materialCode")
    private String materialCode;
    @JsonProperty("materialName")
    private String materialName;

    public static MaterialDto fromEntity(Material material){
        MaterialDto dto = new MaterialDto();
        dto.setErpMaterialNo(material.getErpMaterialNo());
        dto.setMaterialCode(material.getMaterialCode());
        dto.setMaterialName(material.getMaterialName());
        return dto;
    }
}
