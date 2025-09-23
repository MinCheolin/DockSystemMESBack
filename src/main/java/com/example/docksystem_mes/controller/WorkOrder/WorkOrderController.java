package com.example.docksystem_mes.controller.WorkOrder;

import com.example.docksystem_mes.dto.WorkOrder.WorkOrderCreateRequestDto;
import com.example.docksystem_mes.dto.WorkOrder.WorkOrderResponseDto;
import com.example.docksystem_mes.dto.WorkOrder.WorkOrderUpdateRequestDto;
import com.example.docksystem_mes.entity.WorkOrder.WorkOrder;
import com.example.docksystem_mes.service.WorkOrder.WorkOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mes/v1/work_orders")
public class WorkOrderController {
    private final WorkOrderService workOrderService;

    @GetMapping
    public ResponseEntity<List<WorkOrderResponseDto>> getAllWorkOrder(){
        List<WorkOrderResponseDto> WorkOrders = workOrderService.getAllWorkOrder();
        return ResponseEntity.ok(WorkOrders);
    }

    @PostMapping
    public WorkOrder createWorkOrder(@RequestBody WorkOrderCreateRequestDto requestDto){
        return workOrderService.createWorkOrder(requestDto);
    }

    @PutMapping("/{id}")
    public WorkOrderResponseDto updateWorkOrder(@PathVariable("id")Long woNo,
                                                @RequestBody WorkOrderUpdateRequestDto requestDto){
        WorkOrder updateworkOrder = workOrderService.updateWorkOrder(woNo,requestDto);
        return WorkOrderResponseDto.fromEntity(updateworkOrder);
    }

    @DeleteMapping("/{woNo}")
    public ResponseEntity<Void> deleteWorkOrder(@PathVariable("woNo")Long woNo){
        workOrderService.deleteWorkOrder(woNo);
        return ResponseEntity.noContent().build();
    }
}
