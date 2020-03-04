package com.stoom.backend.converter;

import com.stoom.backend.dto.AddressDTO;
import com.stoom.backend.entity.Address;

import java.util.Optional;

public class ConverterHelper {

    public static AddressDTO convertAddressToDTO(Address address){
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setStreetName(address.getStreetName());
        addressDTO.setNumber(address.getNumber());
        addressDTO.setComplement(address.getComplement());
        addressDTO.setNeighbourhood(address.getNeighbourhood());
        addressDTO.setCity(address.getCity());
        addressDTO.setState(address.getState());
        addressDTO.setCountry(address.getCountry());
        addressDTO.setZipCode(address.getZipCode());
        addressDTO.setLatitude(address.getLatitude());
        addressDTO.setLongitude(address.getLongitude());
        return addressDTO;
    }

    public static Address convertAddressDTOToEntity(Address addressDTO, Address addressBD){
        addressBD.setStreetName(addressDTO.getStreetName());
        addressBD.setNumber(addressDTO.getNumber());
        addressBD.setComplement(addressDTO.getComplement());
        addressBD.setNeighbourhood(addressDTO.getNeighbourhood());
        addressBD.setCity(addressDTO.getCity());
        addressBD.setState(addressDTO.getState());
        addressBD.setCountry(addressDTO.getCountry());
        addressBD.setZipCode(addressDTO.getZipCode());
        addressBD.setLatitude(addressDTO.getLatitude());
        addressBD.setLongitude(addressDTO.getLongitude());
        return addressBD;
    }
}
