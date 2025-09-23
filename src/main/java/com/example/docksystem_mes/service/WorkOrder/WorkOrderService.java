package com.example.docksystem_mes.service.WorkOrder;

import com.example.docksystem_mes.dto.Process.ProcessResponseDto;
import com.example.docksystem_mes.dto.WorkOrder.WorkOrderCreateRequestDto;
import com.example.docksystem_mes.dto.WorkOrder.WorkOrderResponseDto;
import com.example.docksystem_mes.dto.WorkOrder.WorkOrderUpdateRequestDto;
import com.example.docksystem_mes.entity.Material.Material;
import com.example.docksystem_mes.entity.Process.MesProcess;
import com.example.docksystem_mes.entity.WorkOrder.WorkOrder;
import com.example.docksystem_mes.repository.Material.MaterialRepository;
import com.example.docksystem_mes.repository.Process.ProcessRepository;
import com.example.docksystem_mes.repository.WorkOrder.WorkOrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class WorkOrderService {
    private final WorkOrderRepository workOrderRepository;
    private final ProcessRepository processRepository;
    private final MaterialRepository materialRepository;

    @Autowired
    public WorkOrderService(WorkOrderRepository workOrderRepository,
                            ProcessRepository processRepository,
                            MaterialRepository materialRepository){
        this.workOrderRepository = workOrderRepository;
        this.processRepository = processRepository;
        this.materialRepository = materialRepository;
    }

    public WorkOrder createWorkOrder(WorkOrderCreateRequestDto requestDto){
        WorkOrder workOrder = new WorkOrder();
        workOrder.setWoStartDate(requestDto.getWoStartDate());
        workOrder.setWoEndDate(requestDto.getWoEndDate());
        workOrder.setWoDetail(requestDto.getWoDetail());
        workOrder.setWoDescription(requestDto.getWoDescription());
        workOrder.setType(requestDto.getType());
        MesProcess process = processRepository.findById(requestDto.getProcessNo())
                .orElseThrow(()-> new EntityNotFoundException("존재하지 않는 공정입니다." + requestDto.getProcessNo()));
        workOrder.setMesProcess(process);
        Material material = materialRepository.findById(requestDto.getMaterialNo())
                .orElseThrow(()-> new EntityNotFoundException("존재하지 않는 자재입니다." + requestDto.getMaterialNo()));
        workOrder.setMaterial(material);
        return workOrderRepository.save(workOrder);
    }

    public List<WorkOrderResponseDto> getAllWorkOrder(){
        return  workOrderRepository.findAll().stream()
                .map(WorkOrderResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    public void deleteWorkOrder(Long woNo){
        WorkOrder existingWorkOrder = workOrderRepository.findById(woNo)
                .orElseThrow(()->new EntityNotFoundException("해당 No의 작업 지시를 찾을 수 없습니다."));
        workOrderRepository.delete(existingWorkOrder);
    }

    public WorkOrder updateWorkOrder(Long woNo, WorkOrderUpdateRequestDto requestDto){
        WorkOrder existingWorkOrder = workOrderRepository.findById(woNo)
                .orElseThrow(()->new EntityNotFoundException("해당 No의 작업 지시를 찾을 수 없습니다."));
        MesProcess existingProcess = processRepository.findById(requestDto.getProcessNo())
                .orElseThrow(()->new EntityNotFoundException("해당 No의 공정를 찾을 수 없습니다."));
        Material existingMaterial = materialRepository.findById(requestDto.getMaterialNo())
                .orElseThrow(()->new EntityNotFoundException("해당 No의 자재를 찾을 수 없습니다."));
        existingWorkOrder.setWoStartDate(requestDto.getWoStartDate());
        existingWorkOrder.setWoEndDate(requestDto.getWoEndDate());
        existingWorkOrder.setWoDetail(requestDto.getWoDetail());
        existingWorkOrder.setWoDescription(requestDto.getWoDescription());
        existingWorkOrder.setType(requestDto.getType());
        existingWorkOrder.setMesProcess(existingProcess);
        existingWorkOrder.setMaterial(existingMaterial);
        return existingWorkOrder;
    }
}
