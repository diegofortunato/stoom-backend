package com.stoom.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name= "Address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {

    private static final long serialVersionUID = 3001782215680858821L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "streetName")
    @NotBlank(message = "Street Name is mandatory")
    private String streetName;

    @Column(name = "number")
    @NotBlank(message = "Number is mandatory")
    private Long number;

    @Column(name = "complement")
    private String complement;

    @Column(name = "neighbourhood")
    @NotBlank(message = "Neighbourhood is mandatory")
    private String neighbourhood;

    @Column(name = "city")
    @NotBlank(message = "City is mandatory")
    private String city;

    @Column(name = "state")
    @NotBlank(message = "State is mandatory")
    private String state;

    @Column(name = "country")
    @NotBlank(message = "Country is mandatory")
    private String country;

    @Column(name = "zipCode")
    @NotBlank(message = "ZipCode is mandatory")
    private Long zipCode;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, streetName, number, complement, neighbourhood, city, state, country, zipCode, latitude, longitude);
    }
}
