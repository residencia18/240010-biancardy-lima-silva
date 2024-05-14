package br.com.cepedi.Voll.api.audit.entitys;

import br.com.cepedi.Voll.api.audit.record.DataRegisterAudit;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "audit_log")
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "EventName must not be null")
    @Column(unique = true, nullable = false)
    private String eventName;
    
    @Size(max = 255) 
    private String eventDescription;
    
    private Instant timestamp; 
    private Long userId;
    private String userName;
    private String affectedResource;
    private String origin;

    public AuditLog(DataRegisterAudit data){
        this.eventName = data.eventName();
        this.eventDescription = data.eventDescription();
        this.timestamp = Instant.now();
        this.userId = data.userId();
        this.userName = data.userName();
        this.affectedResource = data.affectedResource();
        this.origin = data.origin();
    }
}
