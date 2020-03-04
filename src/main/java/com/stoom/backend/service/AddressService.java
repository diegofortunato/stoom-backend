package com.stoom.backend.service;

import com.stoom.backend.dto.AddressDTO;
import com.stoom.backend.entity.Address;
import com.stoom.backend.exception.ApplicationException;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    List<AddressDTO> getAllAddress() throws ApplicationException;
    Optional<AddressDTO> getOneAddress(Long id) throws ApplicationException;
    Optional<AddressDTO> createAddress(Address address) throws ApplicationException;
    Optional<AddressDTO> updateAddress(Address address, Long id) throws ApplicationException;
    Optional<AddressDTO> deleteAddress(Long id) throws ApplicationException;
}
