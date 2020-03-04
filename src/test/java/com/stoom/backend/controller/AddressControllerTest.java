package com.stoom.backend.controller;

import com.stoom.backend.dto.AddressDTO;
import com.stoom.backend.entity.Address;
import com.stoom.backend.exception.ApplicationException;
import com.stoom.backend.service.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AddressControllerTest {

    private AddressController addressController;

    @Mock
    private AddressService addressService;

    @BeforeEach
    public void before() {
        this.addressController = new AddressController(this.addressService);
    }

    @Test
    public void getAllAddressTest() throws ApplicationException {
        ResponseEntity response = createAddressRequest(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ResponseEntity responseEntity = getAllAddressRequest();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void getOneAddressTest() throws ApplicationException {
        ResponseEntity response = createAddressRequest(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ResponseEntity responseEntity = getOneAddressRequest(1L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void createAddressTest() throws ApplicationException {
        final ResponseEntity response = createAddressRequest(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void updateAddressTest() throws ApplicationException {
        ResponseEntity response = createAddressRequest(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        response = updateAddressRequest(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void deleteAddressTest() throws ApplicationException {
        ResponseEntity response = createAddressRequest(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        response = deleteAddressRequest(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    private ResponseEntity createAddressRequest(Long id) throws ApplicationException {
        Address params = new Address();
        final AtomicReference<Address> atomicAgeParams = new AtomicReference<>();
        Mockito.doAnswer(invocationOnMock -> {
            final Address argument = invocationOnMock.getArgument(0);
            atomicAgeParams.set(argument);
            Optional<AddressDTO> result = Optional.of(new AddressDTO());
            result.get().setId(id);
            return result;
        }).when(this.addressService).createAddress(Mockito.eq(params));

        return this.addressController.createAddress(params);
    }

    private ResponseEntity getOneAddressRequest(Long id) throws EntityNotFoundException, ApplicationException {
        final AtomicReference<Long> atomicAgeParams = new AtomicReference<>();
        Mockito.doAnswer(invocationOnMock -> {
            final Long argument = invocationOnMock.getArgument(0);
            atomicAgeParams.set(argument);
            Optional<AddressDTO> result = Optional.of(new AddressDTO());
            result.get().setId(id);
            return result;
        }).when(this.addressService).getOneAddress(id);

        return this.addressController.getOneAddress(id);
    }

    private ResponseEntity getAllAddressRequest() throws EntityNotFoundException, ApplicationException {
        Mockito.doAnswer(invocationOnMock -> {
            return new ArrayList<>();
        }).when(this.addressService).getAllAddress();

        return this.addressController.getAllAddress();
    }

    private ResponseEntity updateAddressRequest(Long id) throws EntityNotFoundException, ApplicationException {
        Address params = new Address();

        final AtomicReference<Address> atomicAgeParams = new AtomicReference<>();
        Mockito.doAnswer(invocationOnMock -> {
            final Address argument = invocationOnMock.getArgument(0);
            atomicAgeParams.set(argument);
            Optional<AddressDTO> result = Optional.of(new AddressDTO());
            result.get().setId(id);
            return result;
        }).when(this.addressService).updateAddress(params, id);

        return this.addressController.updateAddress(params, id);
    }

    private ResponseEntity deleteAddressRequest(Long id) throws EntityNotFoundException, ApplicationException {
        final AtomicReference<Long> atomicAgeParams = new AtomicReference<>();
        Mockito.doAnswer(invocationOnMock -> {
            final Long argument = invocationOnMock.getArgument(0);
            atomicAgeParams.set(argument);
            Optional<AddressDTO> result = Optional.of(new AddressDTO());
            result.get().setId(id);
            return result;
        }).when(this.addressService).deleteAddress(id);

        return this.addressController.deleteAddress(id);
    }
}
