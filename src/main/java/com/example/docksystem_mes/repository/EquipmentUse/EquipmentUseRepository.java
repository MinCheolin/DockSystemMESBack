package com.example.docksystem_mes.repository.EquipmentUse;

import com.example.docksystem_mes.entity.EquipmentUse.EquipmentUse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EquipmentUseRepository extends JpaRepository<EquipmentUse,Long> {
    Optional<EquipmentUse> findByWoNo(Long woNo);
}
