package com.example.docksystem_mes.controller.Equipment;

import com.example.docksystem_mes.dto.Equipment.FromErpEquipmentDto;
import com.example.docksystem_mes.entity.Equipment.Equipment;
import com.example.docksystem_mes.service.Equipment.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mes/v1/equipments")
@RequiredArgsConstructor
public class EquipmentController {
    private final EquipmentService equipmentService;

    @GetMapping
    public ResponseEntity<List<FromErpEquipmentDto>> getAllEquipment(){
        List<FromErpEquipmentDto> equipments = equipmentService.getAllEquipment();
        return ResponseEntity.ok(equipments);
    }

    @PostMapping
    public ResponseEntity<Equipment> createEquipment(@RequestBody FromErpEquipmentDto dto){
        Equipment equipment = equipmentService.saveOrUpdate(dto);
        return new ResponseEntity<>(equipment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{erpEquipNo}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable("erpEquipNo") String erpEquipNo){
        equipmentService.deleteEquipment(erpEquipNo);
        return ResponseEntity.noContent().build();
    }
}
