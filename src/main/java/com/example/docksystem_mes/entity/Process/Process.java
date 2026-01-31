package com.example.docksystem_mes.entity.Process;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Process {

   @Id
   @GeneratedValue (strategy = GenerationType.IDENTITY)
   private Long processNo;
    @Column(nullable = false)
   private String processName;
   @Enumerated(EnumType.STRING)
   @Column(nullable = false)
   private ProcessStatus processStatus;
   private String processDescription;

    public void UpdateProcess(){

    }
}
