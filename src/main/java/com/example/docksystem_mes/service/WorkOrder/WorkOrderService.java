package com.example.docksystem_mes.service.WorkOrder;

import com.example.docksystem_mes.dto.Equipment.ToErpEquipmentDto;
import com.example.docksystem_mes.dto.WorkOrder.WorkOrderCreateRequestDto;
import com.example.docksystem_mes.dto.WorkOrder.WorkOrderResponseDto;
import com.example.docksystem_mes.dto.WorkOrder.WorkOrderTypeUpdateDto;
import com.example.docksystem_mes.dto.WorkOrder.WorkOrderUpdateRequestDto;
import com.example.docksystem_mes.entity.Equipment.Equipment;
import com.example.docksystem_mes.entity.Material.Material;
import com.example.docksystem_mes.entity.WorkOrder.WorkOrder;
import com.example.docksystem_mes.entity.WorkOrder.WorkOrderType;
import com.example.docksystem_mes.repository.Equipment.EquipmentRepository;
import com.example.docksystem_mes.repository.Material.MaterialRepository;
import com.example.docksystem_mes.repository.WorkOrder.WorkOrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class WorkOrderService {
    private final WorkOrderRepository workOrderRepository;
    private final EquipmentRepository equipmentRepository;
    private final MaterialRepository materialRepository;
    private final RestTemplate restTemplate;
    @Value("${erp.base-url}")
    private String erpApiUrl;

    @Autowired
    public WorkOrderService(WorkOrderRepository workOrderRepository,
                            EquipmentRepository equipmentRepository,
                            MaterialRepository materialRepository,
                            RestTemplate restTemplate){
        this.workOrderRepository = workOrderRepository;
        this.equipmentRepository = equipmentRepository;
        this.materialRepository = materialRepository;
        this.restTemplate = restTemplate;
    }

    public WorkOrder createWorkOrder(WorkOrderCreateRequestDto requestDto){

        Equipment equipment = equipmentRepository.findById(requestDto.getEquipNo())
                .orElseThrow(()->new EntityNotFoundException("해당 no의 장비를 찾을 수 없습니다."));

        boolean inUse = workOrderRepository.existsByEquipmentAndWoStartDateLessThanEqualAndWoEndDateGreaterThanEqual(equipment,requestDto.getWoStartDate(),requestDto.getWoEndDate());

        if(inUse){
            throw new IllegalStateException("해당 장비는 이미 선택한 기간에 사용중입니다.");
        }

        WorkOrder workOrder = new WorkOrder();
        workOrder.setWoName(requestDto.getWoName());
        workOrder.setWoStartDate(requestDto.getWoStartDate());
        workOrder.setWoEndDate(requestDto.getWoEndDate());
        workOrder.setWoDetail(requestDto.getWoDetail());
        workOrder.setWoDescription(requestDto.getWoDescription());
        workOrder.setType(requestDto.getType());
        workOrder.setPpNo(requestDto.getPpNo());
        workOrder.setEquipment(equipment);

       return workOrderRepository.save(workOrder) ;
    }

    public List<WorkOrderResponseDto> getAllWorkOrder(){
        return  workOrderRepository.findAll().stream()
                .map(WorkOrderResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    public WorkOrder getWorkOrderByNo(Long woNo){
        return workOrderRepository.findById(woNo)
                .orElseThrow(() -> new EntityNotFoundException("해당 작업 지시를 찾을 수 없습니다. woNo: " + woNo));
    }

    public void deleteWorkOrder(Long woNo){
        WorkOrder existingWorkOrder = workOrderRepository.findById(woNo)
                .orElseThrow(()->new EntityNotFoundException("해당 No의 작업 지시를 찾을 수 없습니다."));
        workOrderRepository.delete(existingWorkOrder);
    }

    public WorkOrder updateWorkOrder(Long woNo, WorkOrderUpdateRequestDto requestDto){
        WorkOrder existingWorkOrder = workOrderRepository.findById(woNo)
                .orElseThrow(()->new EntityNotFoundException("해당 no의 작업지시를 찾을 수 없습니다."));
        Equipment existingEquipment = equipmentRepository.findById(requestDto.getEquipNo())
                .orElseThrow(()->new EntityNotFoundException("해당 no의 장비를 찾을 수 없습니다."));
        existingWorkOrder.setWoStartDate(requestDto.getWoStartDate());
        existingWorkOrder.setWoEndDate(requestDto.getWoEndDate());
        existingWorkOrder.setWoDetail(requestDto.getWoDetail());
        existingWorkOrder.setWoDescription(requestDto.getWoDescription());
        existingWorkOrder.setType(requestDto.getType());
        existingWorkOrder.setPpNo(requestDto.getPpNo());
        existingWorkOrder.setEquipment(existingEquipment);

        return workOrderRepository.save(existingWorkOrder);
    }

    public void sendEquipmentERP(ToErpEquipmentDto dto){
        try {
            restTemplate.postForObject(erpApiUrl + "/equipments/mes/update",dto,String.class);
            System.out.println("전송 성공");
        }catch (Exception e){
            throw new RuntimeException("전송 실패");
        }
    }

    public void updateWorkOrderType(Long woNo, WorkOrderTypeUpdateDto dto){
        WorkOrder workOrder = workOrderRepository.findById(woNo).orElseThrow(() -> new EntityNotFoundException("WorkOrder not found "));
        workOrder.setType(dto.getType());
    }
}
