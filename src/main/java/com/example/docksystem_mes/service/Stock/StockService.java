package com.example.docksystem_mes.service.Stock;

import com.example.docksystem_mes.dto.Stock.StockCreateDto;
import com.example.docksystem_mes.dto.Stock.StockResponseDto;
import com.example.docksystem_mes.dto.Stock.StockUpdateDto;
import com.example.docksystem_mes.entity.Stock.Stock;
import com.example.docksystem_mes.repository.Stock.StockRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepo;


    public List<StockResponseDto> getAllStocks(){
        return stockRepo.findAll().stream().map(StockResponseDto::fromEntity).collect(Collectors.toList());
    }

    public void createStock(StockCreateDto dto){
        stockRepo.save(dto.toEntity());
    }

    public void updateStock(Long StockNo, StockUpdateDto dto){
        Stock stock = stockRepo.findById(StockNo)
                .orElseThrow(()->new EntityNotFoundException("재고를 찾을 수 없습니다."));
        stock.CountUpdate(dto.getNormalCount(),dto.getErrorCount());
        stockRepo.save(stock);
    }

    public void deleteStock(){

    }

}
