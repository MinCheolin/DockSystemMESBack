package com.example.docksystem_mes.dto.QualityControl;

import com.example.docksystem_mes.entity.QualityControl.QualityControl;
import com.example.docksystem_mes.entity.QualityControl.QualityControlType;
import com.example.docksystem_mes.entity.Stock.Stock;
import com.example.docksystem_mes.entity.WorkOrder.WorkOrder;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class QualityControlCreateRequestDto {
    @NotBlank(message = "갯수 입력은 필수입니다.")
    private int totalQuantity;
    @NotBlank(message = "품질 검사 상태는 필수입니다.")
    private QualityControlType type;
    @NotBlank(message = "작업 지시는 필수입니다.")
    private Long woNo;
    @NotBlank(message = "재고 품목은 필수입니다.")
    private Long stockNo;

    public QualityControl toEntity(WorkOrder wo, Stock stock){
        QualityControl qcEntity = new QualityControl();
        qcEntity.setTotalQuantity(this.getTotalQuantity());
        qcEntity.setType(this.getType());
        qcEntity.setStock(stock);
        qcEntity.setWorkOrder(wo);
        return qcEntity;
    }

}
