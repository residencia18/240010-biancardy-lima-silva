package br.com.cepedi.Voll.api.model.entities;

import br.com.cepedi.Voll.api.model.records.doctor.input.DataRegisterDoctor;
import br.com.cepedi.Voll.api.model.records.doctor.input.DataUpdateDoctor;
import br.com.cepedi.Voll.api.model.records.doctor.input.Specialty;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phoneNumber;

    private String crm;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Embedded
    private Address address;

    private Boolean activated;

    public Doctor(DataRegisterDoctor data) {
        this.name = data.name();
        this.email = data.email();
        this.crm = data.crm();
        this.phoneNumber = data.phoneNumber();
        this.specialty = data.specialty();
        this.address = new Address(data.dataAddress());
        this.activated = true;
    }

    public void updateData(DataUpdateDoctor data) {
        if (Objects.nonNull(data.name())) {
            this.name = data.name();
        }

        if (Objects.nonNull(data.phoneNumber())) {
            this.phoneNumber = data.phoneNumber();
        }

        if (Objects.nonNull(data.dataAddress())) {
            this.address.updateData(data.dataAddress());
        }
    }

    public void logicalDelete() {
        this.activated = false;
    }
}
