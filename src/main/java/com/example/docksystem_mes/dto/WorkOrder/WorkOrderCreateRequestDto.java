package com.example.docksystem_mes.dto.WorkOrder;

import com.example.docksystem_mes.entity.WorkOrder.WorkOrderType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class WorkOrderCreateRequestDto {

    @NotBlank(message = "작업 지시명은 필수입니다.")
    private String woName;
    @NotBlank(message = "시작일은 필수입니다.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date woStartDate;
    @NotBlank(message = "마감일은 필수입니다.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date woEndDate;
    @NotBlank(message = "작업내용은 필수입니다.")
    private String woDetail;
    @NotBlank(message = "특이사항은 필수입니다.")
    private String woDescription;
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "작업 지시 상태는 필수입니다.")
    private WorkOrderType type;
    @NotBlank(message = "생산 계획 번호는 필수입니다.")
    private String ppNo;
    @NotNull(message = "장비는 필수입니다.")
    private Long equipNo;
    @NotNull(message = "자재는 필수입니다.")
    private Long materialNo;
}
