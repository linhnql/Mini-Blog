package com.miniblog.poc.service.validator.contact;

import com.miniblog.poc.api.model.ContactRequest;
import com.miniblog.poc.service.exception.BadRequestException;
import com.miniblog.poc.service.exception.EntityNotFoundException;
import com.miniblog.poc.service.repository.ContactRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ContactValidator {
    private static final String CONTACT_DOES_NOT_EXIST = "Contact does not exist";

    private static final String NAME_REQUEST = "Name is requested";

    private static final String EMAIL_REQUEST = "Email is required";

    private static final String MESSENGER_REQUEST = "Messenger is required";

    private final ContactRepository repository;

    public void validateContactExist(String id) {
        if (repository.existsById(id)) {return;}

        throw new EntityNotFoundException(CONTACT_DOES_NOT_EXIST);
    }

    public void validateAddContact(ContactRequest request) {
        checkRequiredField(request.getName(), NAME_REQUEST);
        checkRequiredField(request.getEmail(), EMAIL_REQUEST);
        checkRequiredField(request.getMessenger(), MESSENGER_REQUEST);
    }

    private void checkRequiredField(String value, String errorMsg) {
        if (null == value || value.trim().isEmpty()) {
            throw new BadRequestException(errorMsg);
        }
    }

}
