package br.com.cepedi.Voll.api.controller.V1;

import br.com.cepedi.Voll.api.model.records.appointment.input.DataCancelAppointment;
import br.com.cepedi.Voll.api.model.records.appointment.input.DataRegisterAppointment;
import br.com.cepedi.Voll.api.model.records.appointment.details.DataDetailsAppointment;
import br.com.cepedi.Voll.api.services.appointment.AppointmentService;
import br.com.cepedi.Voll.api.audit.record.DataRegisterAudit;
import br.com.cepedi.Voll.api.audit.service.AuditService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/employees")
@SecurityRequirement(name = "bearer-key")
public class AppointmentControllerV1 {

    private static final Logger log = LoggerFactory.getLogger(AppointmentControllerV1.class);

    @Autowired
    private AppointmentService service;
    
    @Autowired
    private AuditService auditService;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> schedule(@RequestBody @Valid DataRegisterAppointment data) {
        DataDetailsAppointment details = service.register(data);
        DataRegisterAudit auditData = new DataRegisterAudit("scheduleAppointment", "Schedule appointment", null, null, null, null);
        auditService.logEvent(auditData);
        return ResponseEntity.ok(details);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Object> cancel(@RequestBody @Valid DataCancelAppointment data) {
        service.cancel(data);
        DataRegisterAudit auditData = new DataRegisterAudit("cancelAppointment", "Cancel appointment", null, null, null, null);
        auditService.logEvent(auditData);
        return ResponseEntity.noContent().build();
    }

}
