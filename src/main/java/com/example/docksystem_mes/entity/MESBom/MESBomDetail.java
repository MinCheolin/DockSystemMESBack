package com.example.docksystem_mes.entity.MESBom;

import com.example.docksystem_mes.entity.MESMaterial.MESMaterial;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "mes_bom_details")
public class MESBomDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mesBomDetailNo;
    @Column(nullable = false)
    private String erpBomDetailNo;
    private Long mesBomDetailCount;
    @ManyToOne
    @JoinColumn(name = "mes_bom_no")
    private MESBom mesBom;
    @OneToOne
    @JoinColumn(name = "mes_material_no")
    private MESMaterial mesMaterial;
}
