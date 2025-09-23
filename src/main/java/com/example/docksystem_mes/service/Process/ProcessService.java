package com.example.docksystem_mes.service.Process;

import com.example.docksystem_mes.dto.Process.ProcessCreateRequestDto;
import com.example.docksystem_mes.dto.Process.ProcessResponseDto;
import com.example.docksystem_mes.dto.Process.ProcessUpdateRequestDto;
import com.example.docksystem_mes.entity.Equipment.Equipment;
import com.example.docksystem_mes.repository.Equipment.EquipmentRepository;
import com.example.docksystem_mes.repository.Process.ProcessRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import com.example.docksystem_mes.entity.Process.MesProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProcessService {
    private final ProcessRepository processRepository;
    private final EquipmentRepository equipmentRepository;

    @Autowired
    public ProcessService(ProcessRepository processRepository,
                          EquipmentRepository equipmentRepository) {
        this.processRepository = processRepository;
        this.equipmentRepository = equipmentRepository;
    }

    public MesProcess createProcess(ProcessCreateRequestDto requestDto) {
        MesProcess mesProcess = new MesProcess();
        mesProcess.setProcessName(requestDto.getProcessName());
        mesProcess.setType(requestDto.getType());
        mesProcess.setProcessDescription(requestDto.getProcessDescription());
        Equipment equipment = equipmentRepository.findById(requestDto.getEquipNo())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 장비입니다." + requestDto.getEquipNo()));
        mesProcess.setEquipment(equipment);
        return processRepository.save(mesProcess);
    }

    public List<ProcessResponseDto> getAllProcess(){
        return processRepository.findAll().stream()
                .map(ProcessResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    public void deleteProcess(Long processNo){
        MesProcess existingProcess = processRepository.findById(processNo)
                .orElseThrow(()->new EntityNotFoundException("해당 No의 공정을 찾을 수 없습니다."));
        processRepository.delete(existingProcess);
    }

    public MesProcess updateProcess(Long processNo, ProcessUpdateRequestDto requestDto){
        MesProcess existingProcess = processRepository.findById(processNo)
                .orElseThrow(()->new EntityNotFoundException("해당 No의 공정을 찾을 수 없습니다."));
        Equipment equipment = equipmentRepository.findById(requestDto.getEquipNo())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 장비입니다." + requestDto.getEquipNo()));
        existingProcess.setProcessName(requestDto.getProcessName());
        existingProcess.setType(requestDto.getType());
        existingProcess.setProcessDescription(requestDto.getProcessDescription());
        existingProcess.setEquipment(equipment);
        return existingProcess;
    }
}
