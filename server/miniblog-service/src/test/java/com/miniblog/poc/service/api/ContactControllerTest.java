package com.miniblog.poc.service.api;

import com.miniblog.poc.api.model.Contact;
import com.miniblog.poc.api.model.ContactRequest;
import com.miniblog.poc.api.model.Contacts;
import com.miniblog.poc.service.api.contact.ContactController;
import com.miniblog.poc.service.mock.ContactData;
import com.miniblog.poc.service.service.ContactService;
import com.miniblog.poc.service.validator.contact.ContactSearchValidator;
import com.miniblog.poc.service.validator.contact.ContactValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ContactControllerTest {
    private final String API_KEY = "MOCK_API_KEY";

    @InjectMocks
    ContactController controller;

    @Mock
    ContactService service;

    @Mock
    ContactValidator validator;

    @Mock
    ContactSearchValidator searchValidator;

    @Test
    public void testEndpointAddContact() {
        when(service.addContact(any(ContactRequest.class)))
                .thenReturn(ContactData.mockContact());

        ResponseEntity<Contact> responseEntity =
                controller.addContact(API_KEY, ContactData.mockContactRequest());

        assertStatus200(responseEntity.getStatusCode());
        assertContact(Objects.requireNonNull(responseEntity.getBody()));
    }

    @Test
    public void testEndpointGetContactById(){
        when(service.getContactById(anyString()))
                .thenReturn(ContactData.mockContact());

        ResponseEntity<Contact> responseEntity =
                controller.getContactById(API_KEY, ContactData.ID);

        assertStatus200(responseEntity.getStatusCode());
        assertContact(Objects.requireNonNull(responseEntity.getBody()));
    }

    @Test
    public void testEndpointGetAllContacts(){
        when(service.getAllContacts())
                .thenReturn(ContactData.mockContacts());

        ResponseEntity<Contacts> responseEntity =
                controller.getAllContacts(API_KEY, 1, 1);

        assertStatus200(responseEntity.getStatusCode());
        assertContacts(Objects.requireNonNull(responseEntity.getBody()));
    }

    @Test
    public void testEndpointDeleteContactById(){
        ResponseEntity<Contact> responseEntity = controller.deleteContactById(API_KEY, ContactData.ID);

        assertStatus200(responseEntity.getStatusCode());
    }

    private void assertContact(Contact actual) {
        assertThat(actual.getId(), is(ContactData.ID));
        assertThat(actual.getName(), is(ContactData.NAME));
        assertThat(actual.getEmail(), is(ContactData.EMAIL));
        assertThat(actual.getMessenger(), is(ContactData.MESSENGER));
    }

    private void assertContacts(Contacts actual) {
        assertContact(actual.get(0));
    }

    private void assertStatus200(HttpStatus actual) {
        assertThat(actual, is(HttpStatus.OK));
    }
}
