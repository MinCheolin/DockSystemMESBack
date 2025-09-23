package com.example.docksystem_mes.repository.Material;

import com.example.docksystem_mes.entity.Equipment.Equipment;
import com.example.docksystem_mes.entity.Material.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MaterialRepository extends JpaRepository<Material,Long> {
    Optional<Material> findByErpMaterialNo(String erpMaterialNo);
    void deleteByErpMaterialNo(String erpMaterialNo);
}
