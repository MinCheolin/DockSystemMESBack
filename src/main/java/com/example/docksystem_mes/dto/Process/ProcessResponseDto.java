package com.example.docksystem_mes.dto.Process;

import com.example.docksystem_mes.dto.Equipment.EquipmentDto;
import com.example.docksystem_mes.entity.Process.MesProcess;
import com.example.docksystem_mes.entity.Process.MesProcessType;
import lombok.Data;

@Data
public class ProcessResponseDto {
    private Long processNo;
    private String processName;
    private MesProcessType type;
    private String typeLabel;
    private String processDescription;
    private EquipmentDto equipment;

    public static ProcessResponseDto fromEntity(MesProcess mesProcess){
        ProcessResponseDto dto = new ProcessResponseDto();
        dto.setProcessNo(mesProcess.getProcessNo());
        dto.setProcessName(mesProcess.getProcessName());
        dto.setType(mesProcess.getType());
        dto.setTypeLabel(mesProcess.getType().getLabel());
        dto.setProcessDescription(mesProcess.getProcessDescription());
        dto.setEquipment(EquipmentDto.fromEntity(mesProcess.getEquipment()));
        return dto;
    }
}
