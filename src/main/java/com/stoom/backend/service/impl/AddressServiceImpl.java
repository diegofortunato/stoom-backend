package com.stoom.backend.service.impl;

import com.stoom.backend.converter.ConverterHelper;
import com.stoom.backend.dto.AddressDTO;
import com.stoom.backend.entity.Address;
import com.stoom.backend.repository.AddressRepository;
import com.stoom.backend.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<AddressDTO> getAllAddress(){
        return addressRepository.findAll()
                .stream()
                .map(ConverterHelper::convertAddressToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AddressDTO> getOneAddress(Long id) {
        return addressRepository.findById(id)
                .map(ConverterHelper::convertAddressToDTO);
    }

    @Override
    public Optional<AddressDTO> createAddress(Address address) {
        Address addressBD = addressRepository.save(address);
        return Optional.of(ConverterHelper.convertAddressToDTO(addressBD));
    }

    @Override
    public Optional<AddressDTO> updateAddress(Address address, Long id) {
        Optional<Address> addressBD = addressRepository.findById(id);
        if(addressBD.isPresent()){
            address = ConverterHelper.convertAddressDTOToEntity(address, addressBD.get());
            address = addressRepository.save(address);
            return Optional.of(ConverterHelper.convertAddressToDTO(address));
        }else{
            return Optional.empty();
        }
    }

    @Override
    public Optional<AddressDTO> deleteAddress(Long id) {
        Optional<Address> addressBD = addressRepository.findById(id);
        if(addressBD.isPresent()){
            addressRepository.deleteById(id);
            return Optional.of(ConverterHelper.convertAddressToDTO(addressBD.get()));
        }else{
            return Optional.empty();
        }
    }
}