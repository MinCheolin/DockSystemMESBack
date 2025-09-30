package com.example.docksystem_mes.controller.WorkOrder;

import com.example.docksystem_mes.dto.WorkOrder.WorkOrderCreateRequestDto;
import com.example.docksystem_mes.dto.WorkOrder.WorkOrderResponseDto;
import com.example.docksystem_mes.dto.WorkOrder.WorkOrderUpdateRequestDto;
import com.example.docksystem_mes.entity.WorkOrder.WorkOrder;
import com.example.docksystem_mes.service.WorkOrder.WorkOrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/{woNo}")
    public ResponseEntity<WorkOrderResponseDto> getWorkOrder(@PathVariable("woNo")Long woNo){
        WorkOrder workOrder = workOrderService.getWorkOrderByNo(woNo);
        return ResponseEntity.ok(WorkOrderResponseDto.fromEntity(workOrder));
    }

    @PostMapping
    public ResponseEntity<?> createWorkOrder(@RequestBody WorkOrderCreateRequestDto requestDto){
        try{
            WorkOrder workOrder = workOrderService.createWorkOrder(requestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(workOrder);
        }catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message",e.getMessage()));
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message",e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateWorkOrder(@PathVariable("id")Long woNo,
                                                @RequestBody WorkOrderUpdateRequestDto requestDto){
        try{
            workOrderService.updateWorkOrder(woNo,requestDto);
            return ResponseEntity.ok(Map.of("message","작업 지시가 수정되었습니다."));
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message",e.getMessage()));
        }
        catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message",e.getMessage()));
        }
    }

    @DeleteMapping("/{woNo}")
    public ResponseEntity<?> deleteWorkOrder(@PathVariable("woNo")Long woNo){
        try{
            workOrderService.deleteWorkOrder(woNo);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message",e.getMessage()));
        }

    }
}
