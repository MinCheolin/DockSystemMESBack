package com.example.docksystem_mes.entity.WorkOrder;

import com.example.docksystem_mes.entity.Process.Process;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "work_orders")
public class WorkOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long woNo;
    @Column(nullable = false)
    private Date woStartDate;
    @Column(nullable = false)
    private Date woEndDate;
    @Column(nullable = false,columnDefinition = "TEXT")
    private String woDetail;
    @Column(columnDefinition = "TEXT")
    private String woDescription;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private WorkOrderType type;
    @OneToOne
    @JoinColumn(name = "process_no")
    private Process process;
}
