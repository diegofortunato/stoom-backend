package com.stoom.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private Long id;
    private String streetName;
    private Long number;
    private String complement;
    private String neighbourhood;
    private String city;
    private String state;
    private String country;
    private Long zipCode;
    private Long latitude;
    private Long longitude;
}
