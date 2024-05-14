package br.com.cepedi.Voll.api.model.entities;

import br.com.cepedi.Voll.api.model.records.patient.input.DataRegisterPatient;
import br.com.cepedi.Voll.api.model.records.patient.input.DataUpdatePatient;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phoneNumber;

    private String cpf;

    @Embedded
    private Address address;

    private Boolean activated;

    public Patient(DataRegisterPatient data) {
        this.name = data.name();
        this.email = data.email();
        this.phoneNumber = data.phoneNumber();
        this.cpf = data.cpf();
        this.address = new Address(data.dataAddress());
        this.activated = true;
    }

    public void updateData(DataUpdatePatient data) {
        if (Objects.nonNull(data.name())) {
            this.name = data.name();
        }

        if (Objects.nonNull(data.phoneNumber())) {
            this.phoneNumber = data.phoneNumber();
        }

        if (Objects.nonNull(data.dataAddress())) {
            this.address = new Address(data.dataAddress());
        }
    }

    public void logicalDelete() {
        this.activated = false;
    }
}
