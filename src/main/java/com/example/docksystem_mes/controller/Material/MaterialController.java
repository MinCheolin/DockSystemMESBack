package com.example.docksystem_mes.controller.Material;

import com.example.docksystem_mes.dto.Material.MaterialDto;
import com.example.docksystem_mes.entity.Material.Material;
import com.example.docksystem_mes.service.Material.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mes/v1/materials")
@RequiredArgsConstructor
public class MaterialController {
    private final MaterialService materialService;

    @GetMapping
    public ResponseEntity<List<MaterialDto>> getAllMaterial(){
        List<MaterialDto> materials = materialService.getAllMaterial();
        return ResponseEntity.ok(materials);
    }

    @PostMapping
    public ResponseEntity<Material> createMaterial(@RequestBody MaterialDto dto){
        Material material = materialService.saveOrUpdate(dto);
        return new ResponseEntity<>(material, HttpStatus.CREATED);
    }

    @DeleteMapping("/{erpMaterialNo}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable("erpMaterialNo")String erpMaterialNo){
        materialService.deleteMaterial(erpMaterialNo);
        return ResponseEntity.noContent().build();
    }
}
