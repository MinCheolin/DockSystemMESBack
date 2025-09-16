package com.example.docksystem_mes.entity.Process;

import com.example.docksystem_mes.entity.MESProductPlan.MESProductPlan;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "processes")
public class Process {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long processNo;
    @Column(nullable = false,length = 50)
    private String processName;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProcessType type;
    @Column(columnDefinition = "TEXT")
    private String processDescription;
    @ManyToOne
    @JoinColumn(name = "mes_pp_no")
    private MESProductPlan mesProductPlan;
}
