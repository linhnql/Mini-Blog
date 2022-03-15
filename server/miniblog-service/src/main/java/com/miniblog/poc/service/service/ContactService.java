package com.miniblog.poc.service.service;

import com.miniblog.poc.api.model.Contact;
import com.miniblog.poc.api.model.ContactRequest;
import com.miniblog.poc.api.model.Contacts;
import com.miniblog.poc.service.entity.ContactEntity;
import com.miniblog.poc.service.mapper.ContactMapper;
import com.miniblog.poc.service.repository.ContactRepository;
import com.miniblog.poc.service.search.ContactSearch;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContactService {
    private final ContactRepository repository;

    private final ContactMapper mapper;

    private final ContactSearch contactSearch;

    public Contact addContact(ContactRequest req) {
        ContactEntity contactEntity = mapper.mapContactEntityFromContactRequest(req);

        return mapper.mapContactFromContactEntity(repository.save(contactEntity));
    }

    public Contact getContactById(String id){
        ContactEntity contactEntity = repository.getOne(id);

        return mapper.mapContactFromContactEntity(contactEntity);
    }

    public  Contacts getAllContacts(){
        return mapper.mapContactsFromContactEntities(repository.findAll());
    }

    public void deleteContactById(String id){
        repository.deleteById(id);
    }

    public Contacts search(Integer page, Integer pageSize) {
        return mapper.mapContactsFromContactEntities(contactSearch.getContact(page, pageSize));
    }
}
