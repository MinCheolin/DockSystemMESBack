package com.example.docksystem_mes.controller.Process;

import com.example.docksystem_mes.dto.Process.ProcessCreateRequestDto;
import com.example.docksystem_mes.dto.Process.ProcessResponseDto;
import com.example.docksystem_mes.dto.Process.ProcessUpdateRequestDto;
import com.example.docksystem_mes.entity.Process.MesProcess;
import com.example.docksystem_mes.service.Process.ProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mes/v1/processes")
public class ProcessController {
    private final ProcessService processService;

    @GetMapping
    public ResponseEntity<List<ProcessResponseDto>> getAllProcess(){
        List<ProcessResponseDto> processes = processService.getAllProcess();
        return ResponseEntity.ok(processes);
    }

    @PostMapping
    public MesProcess createProcess(@RequestBody ProcessCreateRequestDto requestDto){
        return processService.createProcess(requestDto);
    }

    @PutMapping("/{id}")
    public ProcessResponseDto updateProcess(@PathVariable("id")Long processNo,
                                            @RequestBody ProcessUpdateRequestDto requestDto){
        MesProcess updateProcess = processService.updateProcess(processNo,requestDto);
        return ProcessResponseDto.fromEntity(updateProcess);
    }

    @DeleteMapping("/{processNo}")
    public ResponseEntity<Void> deleteProcess(@PathVariable("processNo")Long processNo){
        processService.deleteProcess(processNo);
        return ResponseEntity.noContent().build();
    }
}
