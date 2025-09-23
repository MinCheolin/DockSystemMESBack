package com.example.docksystem_mes.dto.Process;

import com.example.docksystem_mes.entity.Process.MesProcessType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProcessCreateRequestDto {

    @NotBlank(message = "공정명은 필수입니다.")
    private String processName;
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "공정 유형은 필수입니다.")
    private MesProcessType type;
    @NotBlank(message = "공정 내용은 필수입니다.")
    private String processDescription;
    @NotBlank(message = "장비는 필수입니다.")
    private Long equipNo;
}
