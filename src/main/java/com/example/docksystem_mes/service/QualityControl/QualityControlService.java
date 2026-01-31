package com.example.docksystem_mes.service.QualityControl;

import com.example.docksystem_mes.dto.QualityControl.QualityControlCreateRequestDto;
import com.example.docksystem_mes.dto.QualityControl.QualityControlResponseDto;
import com.example.docksystem_mes.dto.QualityControl.QualityControlUpdateRequestDto;
import com.example.docksystem_mes.dto.WorkOrder.WorkOrderResponseDto;
import com.example.docksystem_mes.entity.QualityControl.QualityControl;
import com.example.docksystem_mes.entity.Stock.Stock;
import com.example.docksystem_mes.entity.WorkOrder.WorkOrder;
import com.example.docksystem_mes.repository.QualityControl.QualityControlRepository;
import com.example.docksystem_mes.repository.Stock.StockRepository;
import com.example.docksystem_mes.repository.WorkOrder.WorkOrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class QualityControlService {
    private final QualityControlRepository qualityControlRepository;
    private final WorkOrderRepository workOrderRepository;
    private final StockRepository stockRepository;


    public QualityControl createQualityControl(QualityControlCreateRequestDto requestDto){
        Stock findStock = stockRepository.findById(requestDto.getStockNo())
                .orElseThrow(()->new EntityNotFoundException("재고를 찾을 수 없습니다."));
        WorkOrder findWo = workOrderRepository.findById(requestDto.getWoNo())
                .orElseThrow(()->new EntityNotFoundException("작업지시를 찾을 수 없습니다."));
        return qualityControlRepository.save(requestDto.toEntity(findWo,findStock));
    }

    public List<QualityControlResponseDto> getAllQualityControl(){
        return qualityControlRepository.findAll().stream()
                .map(QualityControlResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    public void deleteQualityControl(Long qcNo){
        QualityControl existingQualityControl = qualityControlRepository.findById(qcNo)
                .orElseThrow(()->new EntityNotFoundException("해당 No의 품질 관리를 찾을 수 없습니다."));
        qualityControlRepository.delete(existingQualityControl);
    }

    public void updateQualityControl(Long qcNo, QualityControlUpdateRequestDto requestDto){
        QualityControl existingQualityControl = qualityControlRepository.findById(qcNo)
                .orElseThrow(()->new EntityNotFoundException("해당 No의 품질 관리를 찾을 수 없습니다."));
        existingQualityControl.InspectionCompleted(requestDto);
        qualityControlRepository.save(existingQualityControl);
    }
}
