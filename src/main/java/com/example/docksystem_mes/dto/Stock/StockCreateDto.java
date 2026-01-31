package com.example.docksystem_mes.dto.Stock;


import com.example.docksystem_mes.entity.Stock.Stock;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StockCreateDto {
    @NotBlank(message = "재고 이릅은 필수입니다.")
    private String stockName;
    @NotBlank(message = "갯수 입력은 필수입니다.")
    private int normalCount;
    @NotBlank(message = "갯수 입력은 필수입니다.")
    private int errorCount;

    public Stock toEntity(){
        Stock stockEntity = new Stock();
        stockEntity.setStockName(this.getStockName());
        stockEntity.setNormalCount(this.getNormalCount());
        stockEntity.setErrorCount(this.getErrorCount());
        return stockEntity;
    }

}
