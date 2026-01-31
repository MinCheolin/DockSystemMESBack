package com.example.docksystem_mes.dto.Stock;


import com.example.docksystem_mes.entity.Stock.Stock;
import lombok.Data;

@Data
public class StockResponseDto {
    private Long stockNo;
    private String stockName;
    private int normalCount;
    private int errorCount;


    public static StockResponseDto fromEntity(Stock stock){
        StockResponseDto dto = new StockResponseDto();
        dto.setStockNo(stock.getStockNo());
        dto.setStockName(stock.getStockName());
        dto.setErrorCount(stock.getErrorCount());
        dto.setNormalCount(stock.getNormalCount());
        return dto;
    }
}
