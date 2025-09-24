package com.example.docksystem_mes.service.Material;

import com.example.docksystem_mes.dto.Material.MaterialDto;
import com.example.docksystem_mes.entity.Material.Material;
import com.example.docksystem_mes.repository.Material.MaterialRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MaterialService {
    private final MaterialRepository materialRepository;
    private final RestTemplate restTemplate;
    @Value("${erp.base-url}")
    private String erpApiUrl;

    @Autowired
    public MaterialService(MaterialRepository materialRepository,
                           RestTemplate restTemplate){
        this.materialRepository = materialRepository;
        this.restTemplate = restTemplate;
    }

    public List<MaterialDto> getAllMaterial(){
        return materialRepository.findAll().stream()
                .map(MaterialDto::fromEntity)
                .collect(Collectors.toList());
    }

    public void fetchMaterialFromErp(){
        try{
            MaterialDto[] dtos = restTemplate.getForObject(erpApiUrl+"/materials",MaterialDto[].class);
            if(dtos != null){
                for(MaterialDto dto:dtos){
                    saveOrUpdate(dto);
                }
            }
        }catch (Exception e){
            System.err.println("가져오기 실패"+e.getMessage());
        }
    }

    public Material saveOrUpdate(MaterialDto dto){
        Optional<Material> existingMaterial = materialRepository.findByErpMaterialNo(dto.getErpMaterialNo());
        Material material;

        if(existingMaterial.isPresent()){
            material = existingMaterial.get();
            material.setMaterialCode(dto.getMaterialCode());
            material.setMaterialName(dto.getMaterialName());
        }else{
            material = new Material();
            material.setErpMaterialNo(dto.getErpMaterialNo());
            material.setMaterialCode(dto.getMaterialCode());
            material.setMaterialName(dto.getMaterialName());
        }
        return materialRepository.save(material);
    }

    public void deleteMaterial(String erpMaterialNo){
        materialRepository.deleteByErpMaterialNo(erpMaterialNo);
    }
}
