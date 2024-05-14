package br.com.cepedi.Voll.api.model.entities;

import br.com.cepedi.Voll.api.model.records.address.input.DataRegisterAddress;
import lombok.*;

import javax.persistence.Embeddable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Embeddable
public class Address {

    private String publicPlace;

    private String neighborhood;

    private String cep;

    private String city;

    private String uf;

    private String complement;

    private String number;

    public Address(DataRegisterAddress dataAddress) {
        this.publicPlace = dataAddress.getPublicPlace();
        this.neighborhood = dataAddress.getNeighborhood();
        this.cep = dataAddress.getCep();
        this.city = dataAddress.getCity();
        this.uf = dataAddress.getUf();
        this.complement = dataAddress.getComplement();
        this.number = dataAddress.getNumber();
    }

    public void updateData(DataRegisterAddress data) {
        if (data.getPublicPlace() != null) {
            this.publicPlace = data.getPublicPlace();
        }
        if (data.getNeighborhood() != null) {
            this.neighborhood = data.getNeighborhood();
        }
        if (data.getCep() != null) {
            this.cep = data.getCep();
        }
        if (data.getCity() != null) {
            this.city = data.getCity();
        }
        if (data.getUf() != null) {
            this.uf = data.getUf();
        }
        if (data.getComplement() != null) {
            this.complement = data.getComplement();
        }
        if (data.getNumber() != null) {
            this.number = data.getNumber();
        }
    }
}
