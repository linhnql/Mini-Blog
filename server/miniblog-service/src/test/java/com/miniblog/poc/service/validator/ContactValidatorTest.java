package com.miniblog.poc.service.validator;

import com.miniblog.poc.api.model.ContactRequest;
import com.miniblog.poc.service.exception.BadRequestException;
import com.miniblog.poc.service.mock.BlogData;
import com.miniblog.poc.service.mock.ContactData;
import com.miniblog.poc.service.repository.ContactRepository;
import com.miniblog.poc.service.validator.contact.ContactValidator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ContactValidatorTest {
    @InjectMocks
    ContactValidator validator;

    @Mock
    ContactRepository repository;

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void validateAddContact() {
        validator.validateAddContact(ContactData.mockContactRequest());
    }

    @Test
    public void validateContactExist(){
        when(repository.existsById(ContactData.ID)).thenReturn(true);

        validator.validateContactExist(ContactData.ID);
    }

    @Test
    public void validateAddContactShowExceptionWhenNameFieldIsNull() {
        expected.expect(BadRequestException.class);
        expected.expectMessage("Name is requested");

        ContactRequest contactRequest = ContactData.mockContactRequest();
        contactRequest.setName("");

        validator.validateAddContact(contactRequest);
    }

    @Test
    public void validateAddContactShowExceptionWhenEmailFieldIsNull() {
        expected.expect(BadRequestException.class);
        expected.expectMessage("Email is required");

        ContactRequest contactRequest = ContactData.mockContactRequest();
        contactRequest.setEmail("");

        validator.validateAddContact(contactRequest);
    }

    @Test
    public void validateAddContactShowExceptionWhenMesengerFieldIsNull() {
        expected.expect(BadRequestException.class);
        expected.expectMessage("Messenger is required");

        ContactRequest contactRequest = ContactData.mockContactRequest();
        contactRequest.setMessenger("");

        validator.validateAddContact(contactRequest);
    }

}
