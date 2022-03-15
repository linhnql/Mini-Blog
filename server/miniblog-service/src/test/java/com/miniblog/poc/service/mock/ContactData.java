package com.miniblog.poc.service.mock;

import com.miniblog.poc.api.model.Contact;
import com.miniblog.poc.api.model.ContactRequest;
import com.miniblog.poc.api.model.Contacts;
import com.miniblog.poc.service.entity.ContactEntity;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

public class ContactData {
    public static final String ID = "c73095df-a31d-45a0-8d57-8c43108830c7";
    public static final String NAME = "Nguyen Van A";
    public static final String EMAIL = "a.nguyenvan@gmail.com";
    public static final String MESSENGER = "Happy new year 2022";
//    public static final OffsetDateTime SENT_AT = OffsetDateTime.parse("2022-07-01 08:57:44.000000");

    public static Contact mockContact() {
        Contact contact = new Contact();

        contact.setId(ID);
        contact.setName(NAME);
        contact.setEmail(EMAIL);
        contact.setMessenger(MESSENGER);
        contact.setSentAt(OffsetDateTime.now());
        
        return contact;
    }

    public static Contacts mockContacts() {
        Contacts Contacts = new Contacts();

        Contacts.add(mockContact());

        return Contacts;
    }

    public static ContactRequest mockContactRequest() {
        ContactRequest contactRequest = new ContactRequest();

        contactRequest.setName(NAME);
        contactRequest.setEmail(EMAIL);
        contactRequest.setMessenger(MESSENGER);

        return contactRequest;
    }

    public static ContactEntity mockContactEntity() {
        ContactEntity contactEntity = new ContactEntity();

        contactEntity.setId(ID);
        contactEntity.setName(NAME);
        contactEntity.setEmail(EMAIL);
        contactEntity.setMessenger(MESSENGER);
        contactEntity.setSentAt(OffsetDateTime.now());

        return contactEntity;
    }

    public static List<ContactEntity> mockContactEntities() {
        return Arrays.asList(mockContactEntity());
    }
}
