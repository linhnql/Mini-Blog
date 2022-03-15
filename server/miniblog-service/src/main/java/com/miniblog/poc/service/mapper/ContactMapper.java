package com.miniblog.poc.service.mapper;

import com.miniblog.poc.api.model.Contact;
import com.miniblog.poc.api.model.ContactRequest;
import com.miniblog.poc.api.model.Contacts;
import com.miniblog.poc.service.entity.ContactEntity;
import com.miniblog.poc.service.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ContactMapper {
    private final ContactRepository repository;

    @Autowired
    public ContactMapper(ContactRepository repository) {
        this.repository = repository;
    }

    public ContactEntity setupContactEntityFromContactRequest(ContactEntity to, ContactRequest from){
        to.setName(from.getName());
        to.setEmail(from.getEmail());
        to.setMessenger(from.getMessenger());
        to.setSentAt(OffsetDateTime.now());

        return to;
    }

    public ContactEntity mapContactEntityFromContactRequest(ContactRequest from) {
        ContactEntity to = new ContactEntity();

        to.setId(UUID.randomUUID().toString());

        return setupContactEntityFromContactRequest(to, from);
    }

    public ContactEntity mapContactEntityFromContactRequest(String id, ContactRequest from) {
        ContactEntity to = repository.getOne(id);

        return setupContactEntityFromContactRequest(to, from);
    }

    public Contact mapContactFromContactEntity(ContactEntity from) {
        Contact to = new Contact();

        to.setId(from.getId());
        to.setName(from.getName());
        to.setEmail(from.getEmail());
        to.setMessenger(from.getMessenger());
        to.setSentAt(from.getSentAt());

        return to;
    }

    public Contacts mapContactsFromContactEntities(List<ContactEntity> from) {
        return from.stream()
                .map(this::mapContactFromContactEntity)
                .collect(Collectors.toCollection(Contacts::new));
    }
}
