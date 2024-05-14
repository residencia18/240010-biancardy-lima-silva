package br.com.cepedi.Voll.api.controller.V1;

import br.com.cepedi.Voll.api.model.records.doctor.input.DataRegisterDoctor;
import br.com.cepedi.Voll.api.model.records.doctor.input.DataUpdateDoctor;
import br.com.cepedi.Voll.api.model.records.doctor.details.DataDetailsDoctor;
import br.com.cepedi.Voll.api.services.doctor.DoctorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("v1/doctors")
@SecurityRequirement(name = "bearer-key")
public class DoctorControllerV1 {

    private static final Logger log = LoggerFactory.getLogger(DoctorControllerV1.class);

    @Autowired
    private DoctorService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DataDetailsDoctor> register(@RequestBody @Valid DataRegisterDoctor data, UriComponentsBuilder uriBuilder) {
        DataDetailsDoctor details = service.register(data);
        URI uri = uriBuilder.path("/doctors/{id}").buildAndExpand(details.id()).toUri();
        return ResponseEntity.created(uri).body(details);
    }

    @GetMapping
    public ResponseEntity<Page<DataDetailsDoctor>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        Page<DataDetailsDoctor> page = service.list(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataDetailsDoctor> details(@PathVariable Long id) {
        DataDetailsDoctor details = service.details(id);
        return ResponseEntity.ok(details);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DataDetailsDoctor> update(@PathVariable Long id, @RequestBody @Valid DataUpdateDoctor data) {
        DataDetailsDoctor details = service.update(id , data);
        return ResponseEntity.ok(details);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> disabled(@PathVariable Long id) {
        service.disabled(id);
        return ResponseEntity.noContent().build();
    }

}
