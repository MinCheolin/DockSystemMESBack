package com.example.docksystem_mes.dto.Material;

import com.example.docksystem_mes.entity.Material.Material;
import lombok.Data;

@Data
public class MaterialResponseDto {
    private Long materialNo;
    private String materialCode;
    private String materialName;

    public static MaterialResponseDto fromEntity(Material material){
        MaterialResponseDto dto = new MaterialResponseDto();
        dto.setMaterialNo(material.getMaterialNo());
        dto.setMaterialCode(material.getMaterialCode());
        dto.setMaterialName(material.getMaterialName());
        return dto;
    }
}
