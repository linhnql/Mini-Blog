package com.miniblog.poc.service.mapper;

import com.miniblog.poc.api.model.Contact;
import com.miniblog.poc.api.model.ContactRequest;
import com.miniblog.poc.api.model.Contacts;
import com.miniblog.poc.service.entity.ContactEntity;
import com.miniblog.poc.service.mock.ContactData;
import com.miniblog.poc.service.repository.ContactRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ContactMapperTest {

    @InjectMocks
    ContactMapper mapper;

    @Mock
    ContactRepository repository;

    @Test
    public void ensureMapContactEntityFromContactReqForAdd() {

        ContactRequest contactRequest = ContactData.mockContactRequest();

        ContactEntity entity = mapper.mapContactEntityFromContactRequest(contactRequest);

        assertNotNull(entity.getId());
        assertEquals(entity.getName(), contactRequest.getName());
        assertEquals(entity.getEmail(), contactRequest.getEmail());
        assertEquals(entity.getMessenger(), contactRequest.getMessenger());
    }

    @Test
    public void ensureMapContactEntityFromContactReqForUpdate() {
        ContactEntity contactEntity = ContactData.mockContactEntity();

        when(repository.getOne(ContactData.ID)).thenReturn(contactEntity);

        ContactRequest contactRequest = ContactData.mockContactRequest();

        ContactEntity entity = mapper.mapContactEntityFromContactRequest(ContactData.ID, contactRequest);

        assertEquals(entity.getName(), contactRequest.getName());
        assertEquals(entity.getEmail(), contactRequest.getEmail());
        assertEquals(entity.getMessenger(), contactRequest.getMessenger());

    }

    @Test
    public void ensureMapContactFromContactEntity() {
        ContactEntity contactEntity = ContactData.mockContactEntity();

        Contact Contact = mapper.mapContactFromContactEntity(contactEntity);

        assertContact(Contact, contactEntity);
    }

    @Test
    public void ensureMapContactsFromContactEntities() {
        List<ContactEntity> contactEntities = ContactData.mockContactEntities();

        Contacts contacts = mapper.mapContactsFromContactEntities(contactEntities);

        assertContacts(contacts, contactEntities);
    }

    private void assertContact(Contact actual, ContactEntity input) {
        assertEquals(actual.getId(), input.getId());
        assertEquals(actual.getName(), input.getName());
        assertEquals(actual.getEmail(), input.getEmail());
        assertEquals(actual.getMessenger(), input.getMessenger());
//        assertEquals(actual.getSentAt(), input.getSentAt());
    }

    private void assertContacts(Contacts actual, List<ContactEntity> input) {
        assertContact(actual.get(0), input.get(0));
    }

}
