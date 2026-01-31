package com.example.docksystem_mes.controller.Stock;
import com.example.docksystem_mes.dto.Stock.StockCreateDto;
import com.example.docksystem_mes.dto.Stock.StockResponseDto;
import com.example.docksystem_mes.dto.Stock.StockUpdateDto;
import com.example.docksystem_mes.service.Stock.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/mes/v1/stocks")
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    @GetMapping
    public ResponseEntity<List<StockResponseDto>> getAllStocks() {
        List<StockResponseDto> stocks = stockService.getAllStocks();
        return ResponseEntity.ok(stocks);
    }

    @PostMapping
    public ResponseEntity<Objects> createStock(@RequestBody StockCreateDto dto) {
        stockService.createStock(dto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Objects> updateStock(@PathVariable("id") Long stockNo, @RequestBody StockUpdateDto dto){
        stockService.updateStock(stockNo,dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Objects> deleteStock(@PathVariable("id") Long stockNo) {
        return ResponseEntity.noContent().build();
    }
}

