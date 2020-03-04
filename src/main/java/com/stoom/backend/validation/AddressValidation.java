package com.stoom.backend.validation;

import com.stoom.backend.entity.Address;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

public class AddressValidation implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Address.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Class<?> targetClass = target.getClass();
        if(targetClass.equals(Address.class)) validateAddressRequest(target, errors);
    }

    private void validateAddressRequest(Object target, Errors errors) {
        Address address = (Address) target;
        if(Objects.isNull(address.getStreetName())){
            errors.rejectValue("streetName", "Street Name is mandatory");
            return;
        }
        if(Objects.isNull(address.getNumber())){
            errors.rejectValue("number", "Number is mandatory");
        }
        if(Objects.isNull(address.getNeighbourhood())){
            errors.rejectValue("neighbourhood", "Neighbourhood is mandatory");
        }
        if(Objects.isNull(address.getCity())){
            errors.rejectValue("city", "City is mandatory");
        }
        if(Objects.isNull(address.getState())){
            errors.rejectValue("state", "State is mandatory");
        }
        if(Objects.isNull(address.getCountry())){
            errors.rejectValue("country", "Country is mandatory");
        }
        if(Objects.isNull(address.getZipCode())){
            errors.rejectValue("zipCode", "Zipcode is mandatory");
        }
    }

}
