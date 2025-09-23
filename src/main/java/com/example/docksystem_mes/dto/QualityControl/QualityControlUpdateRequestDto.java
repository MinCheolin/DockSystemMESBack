package com.example.docksystem_mes.dto.QualityControl;

import com.example.docksystem_mes.entity.QualityControl.QualityControlType;
import lombok.Data;

@Data
public class QualityControlUpdateRequestDto {
    private QualityControlType type;
    private Long woNo;
}
