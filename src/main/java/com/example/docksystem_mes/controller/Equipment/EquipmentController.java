package com.example.docksystem_mes.controller.Equipment;

import com.example.docksystem_mes.dto.Equipment.EquipmentDto;
import com.example.docksystem_mes.entity.Equipment.Equipment;
import com.example.docksystem_mes.service.Equipment.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mes/v1/equipments")
@RequiredArgsConstructor
public class EquipmentController {
    private final EquipmentService equipmentService;

    @PostMapping
    public ResponseEntity<Equipment> createEquipment(@RequestBody EquipmentDto dto){
        Equipment equipment = equipmentService.saveOrUpdate(dto);
        return new ResponseEntity<>(equipment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{erpEquipNo}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable("erpEquipNo") String erpEquipNo){
        equipmentService.deleteEquipment(erpEquipNo);
        return ResponseEntity.noContent().build();
    }
}
