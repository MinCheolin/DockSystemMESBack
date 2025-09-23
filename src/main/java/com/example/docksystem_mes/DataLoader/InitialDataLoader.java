package com.example.docksystem_mes.DataLoader;

import com.example.docksystem_mes.service.Equipment.EquipmentService;
import com.example.docksystem_mes.service.Material.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitialDataLoader implements CommandLineRunner {
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private MaterialService materialService;

    @Override
    public void run(String... args) throws  Exception{
        equipmentService.fetchEquipmentFromErp();
        materialService.fetchMaterialFromErp();
    }
}
