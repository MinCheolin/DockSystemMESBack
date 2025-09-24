package com.example.docksystem_mes.DataLoader;

import com.example.docksystem_mes.service.Equipment.EquipmentService;
import com.example.docksystem_mes.service.Material.MaterialService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitialDataLoader implements CommandLineRunner {

    private final EquipmentService equipmentService;
    private final MaterialService materialService;

    public InitialDataLoader(EquipmentService equipmentService,
                             MaterialService materialService){
        this.equipmentService = equipmentService;
        this.materialService = materialService;
    }

    @Override
    public void run(String... args) throws  Exception{
        equipmentService.fetchEquipmentFromErp();
        materialService.fetchMaterialFromErp();
    }
}
