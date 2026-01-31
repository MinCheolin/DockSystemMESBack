package com.example.docksystem_mes.controller.QualityControl;

import com.example.docksystem_mes.dto.QualityControl.QualityControlCreateRequestDto;
import com.example.docksystem_mes.dto.QualityControl.QualityControlResponseDto;
import com.example.docksystem_mes.dto.QualityControl.QualityControlUpdateRequestDto;
import com.example.docksystem_mes.entity.QualityControl.QualityControl;
import com.example.docksystem_mes.service.QualityControl.QualityControlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mes/v1/quality_controls")
public class QualityControlController {
    private final QualityControlService qualityControlService;

    @GetMapping
    public ResponseEntity<List<QualityControlResponseDto>> getAllQualityControl(){
        List<QualityControlResponseDto> QualityControls = qualityControlService.getAllQualityControl();
        return ResponseEntity.ok(QualityControls);
    }

    @PostMapping
    public QualityControl createQualityControl(@RequestBody QualityControlCreateRequestDto requestDto){
        return qualityControlService.createQualityControl(requestDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateQualityControl(@PathVariable("id")Long qcNo,
                                                          @RequestBody QualityControlUpdateRequestDto requestDto){
        qualityControlService.updateQualityControl(qcNo,requestDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{qcNo}")
    public ResponseEntity<Void> deleteQualityControl(@PathVariable("qcNo")Long qcNo){
        qualityControlService.deleteQualityControl(qcNo);
        return ResponseEntity.noContent().build();
    }
}
