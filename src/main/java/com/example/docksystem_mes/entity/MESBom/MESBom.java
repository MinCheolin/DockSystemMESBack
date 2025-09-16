package com.example.docksystem_mes.entity.MESBom;

import com.example.docksystem_mes.entity.MESStandardProcess.MESStandardProcess;
import com.example.docksystem_mes.entity.MESVessel.MESVessel;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "mes_bom")
public class MESBom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mesBomNo;
    @Column(nullable = false)
    private String erpBomNo;
    @OneToOne
    @JoinColumn(name = "mes_vessel_no")
    private MESVessel mesVessel;
    @OneToMany
    @JoinColumn(name = "mes_sp_no")
    private MESStandardProcess mesStandardProcess;

}
