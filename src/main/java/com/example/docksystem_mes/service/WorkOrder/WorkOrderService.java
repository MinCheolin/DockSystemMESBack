package com.example.docksystem_mes.service.WorkOrder;

import com.example.docksystem_mes.dto.Equipment.ERPEquipmentDto;
import com.example.docksystem_mes.dto.WorkOrder.WorkOrderCreateRequestDto;
import com.example.docksystem_mes.dto.WorkOrder.WorkOrderResponseDto;
import com.example.docksystem_mes.dto.WorkOrder.WorkOrderUpdateRequestDto;
import com.example.docksystem_mes.entity.Equipment.Equipment;
import com.example.docksystem_mes.entity.EquipmentUse.EquipmentUse;
import com.example.docksystem_mes.entity.Material.Material;
import com.example.docksystem_mes.entity.WorkOrder.WorkOrder;
import com.example.docksystem_mes.entity.WorkOrder.WorkOrderType;
import com.example.docksystem_mes.repository.Equipment.EquipmentRepository;
import com.example.docksystem_mes.repository.EquipmentUse.EquipmentUseRepository;
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
    private final EquipmentUseRepository equipmentUseRepository;
    private final MaterialRepository materialRepository;
    private final RestTemplate restTemplate;
    @Value("${erp.base-url}")
    private String erpApiUrl;

    @Autowired
    public WorkOrderService(WorkOrderRepository workOrderRepository,
                            EquipmentRepository equipmentRepository,
                            EquipmentUseRepository equipmentUseRepository,
                            MaterialRepository materialRepository,
                            RestTemplate restTemplate){
        this.workOrderRepository = workOrderRepository;
        this.equipmentRepository = equipmentRepository;
        this.equipmentUseRepository = equipmentUseRepository;
        this.materialRepository = materialRepository;
        this.restTemplate = restTemplate;
    }

    public WorkOrder createWorkOrder(WorkOrderCreateRequestDto requestDto){
        WorkOrder workOrder = new WorkOrder();
        workOrder.setWoName(requestDto.getWoName());
        workOrder.setWoStartDate(requestDto.getWoStartDate());
        workOrder.setWoEndDate(requestDto.getWoEndDate());
        workOrder.setWoDetail(requestDto.getWoDetail());
        workOrder.setWoDescription(requestDto.getWoDescription());
        workOrder.setType(requestDto.getType());
        workOrder.setPpNo(requestDto.getPpNo());
        Equipment equipment = equipmentRepository.findById(requestDto.getEquipNo())
                .orElseThrow(()->new EntityNotFoundException("존재하지 않는 장비입니다."+requestDto.getEquipNo()));
        workOrder.setEquipment(equipment);
        Material material = materialRepository.findById(requestDto.getMaterialNo())
                .orElseThrow(()-> new EntityNotFoundException("존재하지 않는 자재입니다." + requestDto.getMaterialNo()));
        workOrder.setMaterial(material);

        ERPEquipmentDto dto = new ERPEquipmentDto();
        String erpErpEquipNo = equipment.getErpEquipNo();
        dto.setErpEquipNo(erpErpEquipNo);
        sendEquipmentERP(dto);

        WorkOrder savedWorkOrder = workOrderRepository.save(workOrder);

        EquipmentUse equipmentUse = new EquipmentUse();
        equipmentUse.setEquipNo(equipment.getEquipNo());
        equipmentUse.setErpEquipNo(equipment.getErpEquipNo());
        equipmentUse.setEquipCode(equipment.getEquipCode());
        equipmentUse.setEquipName(equipment.getEquipName());
        equipmentUse.setWoNo(savedWorkOrder.getWoNo());
        equipmentUseRepository.save(equipmentUse);

        equipmentRepository.delete(equipment);

        return savedWorkOrder;
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
        EquipmentUse existingEquipment = equipmentUseRepository.findByWoNo(woNo)
                .orElseThrow(()->new EntityNotFoundException("해당 작업 지시의 장비 사용 현황을 찾을 수 없습니다."));
        Material existingMaterial = materialRepository.findById(requestDto.getMaterialNo())
                .orElseThrow(()->new EntityNotFoundException("해당 No의 자재를 찾을 수 없습니다."));
        existingWorkOrder.setWoName(requestDto.getWoName());
        existingWorkOrder.setWoStartDate(requestDto.getWoStartDate());
        existingWorkOrder.setWoEndDate(requestDto.getWoEndDate());
        existingWorkOrder.setWoDetail(requestDto.getWoDetail());
        existingWorkOrder.setWoDescription(requestDto.getWoDescription());
        existingWorkOrder.setType(requestDto.getType());
        existingWorkOrder.setEquipmentUse(existingEquipment);
        existingWorkOrder.setPpNo(requestDto.getPpNo());
        existingWorkOrder.setMaterial(existingMaterial);

        if(existingWorkOrder.getType() == WorkOrderType.COMPLETE){
            ERPEquipmentDto dto = new ERPEquipmentDto();
            EquipmentUse equipmentUse = equipmentUseRepository.findByWoNo(woNo)
                    .orElseThrow(()->new EntityNotFoundException("해당 작업 지시의 장비 사용 현황이 없습니다."));
            String erpEquipNo = equipmentUse.getErpEquipNo();
            dto.setErpEquipNo(erpEquipNo);
            sendEquipmentERP(dto);

            Equipment equipment = new Equipment();
            equipment.setEquipNo(equipmentUse.getEquipNo());
            equipment.setErpEquipNo(equipmentUse.getErpEquipNo());
            equipment.setEquipCode(equipmentUse.getEquipCode());
            equipment.setEquipName(equipmentUse.getEquipName());

            equipmentRepository.save(equipment);
            equipmentUseRepository.delete(equipmentUse);
        }

        return existingWorkOrder;
    }

    public void sendEquipmentERP(ERPEquipmentDto dto){
        try {
            restTemplate.postForObject(erpApiUrl + "/equipments/mes/update",dto,String.class);
            System.out.println("전송 성공");
        }catch (Exception e){
            throw new RuntimeException("장비 전송 실패");
        }
    }
}
