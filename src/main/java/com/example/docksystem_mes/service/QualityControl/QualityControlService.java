package com.example.docksystem_mes.service.QualityControl;

import com.example.docksystem_mes.dto.QualityControl.QualityControlCreateRequestDto;
import com.example.docksystem_mes.dto.QualityControl.QualityControlResponseDto;
import com.example.docksystem_mes.dto.QualityControl.QualityControlUpdateRequestDto;
import com.example.docksystem_mes.dto.WorkOrder.WorkOrderResponseDto;
import com.example.docksystem_mes.entity.QualityControl.QualityControl;
import com.example.docksystem_mes.entity.WorkOrder.WorkOrder;
import com.example.docksystem_mes.repository.QualityControl.QualityControlRepository;
import com.example.docksystem_mes.repository.WorkOrder.WorkOrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class QualityControlService {
    private final QualityControlRepository qualityControlRepository;
    private final WorkOrderRepository workOrderRepository;

    @Autowired
    public QualityControlService(QualityControlRepository qualityControlRepository,
                                 WorkOrderRepository workOrderRepository){
        this.qualityControlRepository = qualityControlRepository;
        this.workOrderRepository = workOrderRepository;
    }

    public QualityControl createQualityControl(QualityControlCreateRequestDto requestDto){
        QualityControl qualityControl = new QualityControl();
        qualityControl.setType(requestDto.getType());
        WorkOrder workOrder = workOrderRepository.findById(requestDto.getWoNo())
                .orElseThrow(()->new EntityNotFoundException("해당 No의 작업 지시를 찾을 수 없습니다."));
        qualityControl.setWorkOrder(workOrder);
        return qualityControlRepository.save(qualityControl);
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

    public QualityControl updateQualityControl(Long qcNo, QualityControlUpdateRequestDto requestDto){
        QualityControl existingQualityControl = qualityControlRepository.findById(qcNo)
                .orElseThrow(()->new EntityNotFoundException("해당 No의 품질 관리를 찾을 수 없습니다."));
        WorkOrder existingWorkOrder = workOrderRepository.findById(requestDto.getWoNo())
                .orElseThrow(()->new EntityNotFoundException("해당 No의 작업 지시를 찾을 수 없습니다."));
        existingQualityControl.setType(requestDto.getType());
        existingQualityControl.setWorkOrder(existingWorkOrder);
        return existingQualityControl;
    }
}
