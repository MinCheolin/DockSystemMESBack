package com.example.docksystem_mes.repository.Equipment;

import com.example.docksystem_mes.entity.Equipment.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EquipmentRepository extends JpaRepository<Equipment,Long> {
    Optional<Equipment> findByErpEquipNo(String erpEquipNo);
    void deleteByErpEquipNo(String erpEquipNo);
}
