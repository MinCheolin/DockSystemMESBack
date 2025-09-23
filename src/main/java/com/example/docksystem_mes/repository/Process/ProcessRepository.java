package com.example.docksystem_mes.repository.Process;

import com.example.docksystem_mes.entity.Process.MesProcess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessRepository extends JpaRepository<MesProcess,Long> {
}
