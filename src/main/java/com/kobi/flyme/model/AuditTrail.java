package com.kobi.flyme.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity

@Getter
@Setter
@NoArgsConstructor

@Table(name = "audit_trail")
public class AuditTrail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String description;

    @CreationTimestamp
    @Column(name = "action_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime actionDate;

    private static AuditTrail instance;

    private AuditTrail(String description){
        this.description = description;
    }
    public static AuditTrail getInstance (String description){
        if(instance == null)
            return new AuditTrail(description);
        instance.setDescription(description);
        return instance;
    }
}
