package com.stoom.backend.controller;

import com.stoom.backend.dto.AddressDTO;
import com.stoom.backend.entity.Address;
import com.stoom.backend.exception.ApplicationException;
import com.stoom.backend.response.Response;
import com.stoom.backend.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AddressController {

    private AddressService addressService;

    private static final Logger log = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    public AddressController(AddressService addressService){
        this.addressService = addressService;
    }

    /**
     * Returns all registered addresses
     * @return ResponseEntity<Response<List<AddressDTO>>>
     */
    @GetMapping(value = "/address")
    public ResponseEntity<Response<List<AddressDTO>>> getAllAddress() throws ApplicationException {
        log.info("Find all address");
        Response<List<AddressDTO>> response = new Response<List<AddressDTO>>();
        try {
            List<AddressDTO> addressList = this.addressService.getAllAddress();
            if (addressList != null) {
                response.setData(addressList);
                return ResponseEntity.ok(response);
            }else {
                log.info("Address not found");
                response.getErrors().add("Address not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (ApplicationException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Returns a single record specified by id
     * @return ResponseEntity<Response<List<AddressDTO>>>
     */
    @GetMapping(value = "/address/{id}")
    public ResponseEntity<Response<AddressDTO>> getOneAddress(@PathVariable("id") Long id) throws ApplicationException {
        log.info("Find one address: {}", id);
        Response<AddressDTO> response = new Response<AddressDTO>();
        try {
            Optional<AddressDTO> address = this.addressService.getOneAddress(id);
            if (address.isPresent()) {
                response.setData(address.get());
                return ResponseEntity.ok(response);
            }else {
                log.info("Address not found");
                response.getErrors().add("Address not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (ApplicationException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Create a new address
     * @return ResponseEntity<Response<AddressDto>>
     */
    @PostMapping(value = "/address")
    public ResponseEntity<Response<AddressDTO>> createAddress(@Valid @RequestBody Address address)
            throws ApplicationException, MethodArgumentNotValidException {
        log.info("Create new address: {}", address.toString());
        Response<AddressDTO> response = new Response<AddressDTO>();
        try {
            Optional<AddressDTO> addressDTO = this.addressService.createAddress(address);
            if (addressDTO.isPresent()) {
                response.setData(addressDTO.get());
                return ResponseEntity.ok(response);
            }else {
                log.info("Error in create address");
                response.getErrors().add("Error in create address");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }catch (ApplicationException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Update an existing address
     * @return ResponseEntity<Response<AddressDto>>
     */
    @PutMapping(value = "/address/{id}")
    public ResponseEntity<Response<AddressDTO>> updateAddress(@Valid @RequestBody Address address, @PathVariable("id") Long id)
            throws ApplicationException {
        log.info("Update address: {}", id);
        Response<AddressDTO> response = new Response<AddressDTO>();
        try {
            Optional<AddressDTO> addressDTO = this.addressService.updateAddress(address, id);
            if (addressDTO.isPresent()) {
                response.setData(addressDTO.get());
                return ResponseEntity.ok(response);
            }else {
                log.info("Error in update address");
                response.getErrors().add("Error in update address");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }catch (ApplicationException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Delete registered address
     * @return ResponseEntity<Response<AddressDto>>
     */
    @DeleteMapping(value = "/address/{id}")
    public ResponseEntity<Response<AddressDTO>> deleteAddress(@PathVariable("id") Long id) throws ApplicationException {
        log.info("Delete address: {}", id);
        Response<AddressDTO> response = new Response<AddressDTO>();
        try {
            Optional<AddressDTO> address = this.addressService.deleteAddress(id);
            if (address.isPresent()) {
                response.setData(address.get());
                return ResponseEntity.ok(response);
            }else {
                log.info("Error in delete address");
                response.getErrors().add("Error in delete address");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (ApplicationException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}