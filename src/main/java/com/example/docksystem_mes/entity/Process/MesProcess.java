package com.example.docksystem_mes.entity.Process;

import com.example.docksystem_mes.entity.Equipment.Equipment;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "processes")
public class MesProcess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long processNo;
    @Column(nullable = false,length = 50)
    private String processName;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MesProcessType type;
    @Column(columnDefinition = "TEXT")
    private String processDescription;
    @OneToOne
    @JoinColumn(name = "equip_no")
    private Equipment equipment;
}
