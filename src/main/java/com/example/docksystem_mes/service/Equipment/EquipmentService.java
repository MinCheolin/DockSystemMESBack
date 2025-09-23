package com.example.docksystem_mes.service.Equipment;

import com.example.docksystem_mes.dto.Equipment.EquipmentDto;
import com.example.docksystem_mes.entity.Equipment.Equipment;
import com.example.docksystem_mes.repository.Equipment.EquipmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Transactional
public class EquipmentService {
    private EquipmentRepository equipmentRepository;
    private RestTemplate restTemplate;
    @Value("${erp.base-url}")
    private String erpApiUrl;

    @Autowired
    public EquipmentService(EquipmentRepository equipmentRepository,
                            RestTemplate restTemplate){
        this.equipmentRepository = equipmentRepository;
        this.restTemplate = restTemplate;
    }

    public void fetchEquipmentFromErp(){
        try {
            EquipmentDto[] dtos = restTemplate.getForObject(erpApiUrl+"/equipments",EquipmentDto[].class);
            if(dtos != null){
                for(EquipmentDto dto:dtos ){
                    saveOrUpdate(dto);
                }
            }
        }catch(Exception e){
            System.err.println("가져오기 실패"+e.getMessage());
        }
    }

    public Equipment saveOrUpdate(EquipmentDto dto){
        Optional<Equipment> existingEquipment = equipmentRepository.findByErpEquipNo(dto.getErpEquipNo());
        Equipment equipment;

        if(existingEquipment.isPresent()){
            equipment = existingEquipment.get();
            equipment.setEquipCode(dto.getEquipCode());
            equipment.setEquipName(dto.getEquipName());
        }else{
            equipment = new Equipment();
            equipment.setErpEquipNo(dto.getErpEquipNo());
            equipment.setEquipCode(dto.getEquipCode());
            equipment.setEquipName(dto.getEquipName());
        }
        return equipmentRepository.save(equipment);
    }
    public void deleteEquipment(String erpEquipNo){
        equipmentRepository.deleteByErpEquipNo(erpEquipNo);
    }
}
