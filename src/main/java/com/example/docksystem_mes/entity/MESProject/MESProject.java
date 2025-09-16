package com.example.docksystem_mes.entity.MESProject;

import com.example.docksystem_mes.entity.MESCustomer.MESCustomer;
import com.example.docksystem_mes.entity.MESVessel.MESVessel;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "mes_projects")
public class MESProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mesProjectNo;
    @Column(nullable = false)
    private String erpProjectNo;
    private String mesProjectName;
    private Date mesProjectStartDate;
    private Date mesProjectEndDate;
    private MESProjectStatus status;
    @OneToOne
    @JoinColumn(name = "mes_customer_no")
    private MESCustomer mesCustomer;
    @OneToOne
    @JoinColumn(name = "mes_vessel_no")
    private MESVessel mesVessel;
}
