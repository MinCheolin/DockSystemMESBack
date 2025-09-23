package com.example.docksystem_mes.repository.WorkOrder;

import com.example.docksystem_mes.entity.WorkOrder.WorkOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkOrderRepository extends JpaRepository<WorkOrder,Long> {
}
