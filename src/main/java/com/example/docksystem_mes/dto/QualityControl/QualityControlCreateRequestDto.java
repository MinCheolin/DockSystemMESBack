package com.example.docksystem_mes.dto.QualityControl;

import com.example.docksystem_mes.entity.QualityControl.QualityControlType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class QualityControlCreateRequestDto {
    @NotBlank(message = "품질 검사 상태는 필수입니다.")
    private QualityControlType type;
    @NotBlank(message = "작업 지시는 필수입니다.")
    private Long woNo;
}
