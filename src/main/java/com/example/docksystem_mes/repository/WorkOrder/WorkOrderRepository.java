package com.example.docksystem_mes.repository.WorkOrder;

import com.example.docksystem_mes.entity.Equipment.Equipment;
import com.example.docksystem_mes.entity.WorkOrder.WorkOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface WorkOrderRepository extends JpaRepository<WorkOrder,Long> {
    boolean existsByEquipmentAndWoStartDateLessThanEqualAndWoEndDateGreaterThanEqual(
            Equipment equipment, Date woStartDate, Date woEndDate
    );
}
