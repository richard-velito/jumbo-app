package com.jumbo.app.validator;

import com.jumbo.app.ErrorType;
import com.jumbo.app.model.Store;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("storeValidator")
public class StoreValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return StoreValidator.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Store store = (Store)o;

        if (store.getUuid() == null) {
            errors.reject(ErrorType.UuidCannotBeEmpty.toString());
        }

        if (store.getAddressName() == null) {
            errors.reject(ErrorType.AddressCannotBeEmpty.toString());
        }

        if (store.getLongitude() == null) {
            errors.reject(ErrorType.LongitudeCannotBeEmpty.toString());
        }

        if (store.getLatitude() == null) {
            errors.reject(ErrorType.LatitudeCannotBeEmpty.toString());
        }
    }
}
