package com.example.docksystem_mes.entity.MESProductPlan;

import com.example.docksystem_mes.entity.MESBom.MESBom;
import com.example.docksystem_mes.entity.MESProject.MESProject;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
@Table(name = "mes_production_plans")
public class MESProductPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mesPpNo;
    @Column(nullable = false)
    private String erpPpNo;
    private Date mesPpStartDate;
    private Date mesPpEndDate;
    @ManyToOne
    @JoinColumn(name = "mes_project_no")
    private MESProject mesProject;
    @ManyToOne
    @JoinColumn(name = "mes_bom_no")
    private MESBom mesBom;
}
