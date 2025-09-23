package com.example.docksystem_mes.dto.Process;

import com.example.docksystem_mes.entity.Process.MesProcessType;
import lombok.Data;

@Data
public class ProcessUpdateRequestDto {
    private String processName;
    private MesProcessType type;
    private String processDescription;
    private Long equipNo;
}
