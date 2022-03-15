package com.miniblog.poc.service.service;

import com.miniblog.poc.api.model.ContactRequest;
import com.miniblog.poc.service.mapper.ContactMapper;
import com.miniblog.poc.service.mock.ContactData;
import com.miniblog.poc.service.repository.ContactRepository;
import com.miniblog.poc.service.search.ContactSearch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceTest {
    @InjectMocks
    ContactService service;

    @Mock
    ContactRepository repository;

    @Mock
    ContactMapper mapper;

    @Mock
    ContactSearch ContactSearch;

    @Test
    public void addContact() {
        service.addContact(ContactData.mockContactRequest());
    }

    @Test
    public void getContactById(){
        service.getContactById(ContactData.ID);
    }

    @Test
    public void getAllContacts(){
        service.getAllContacts();
    }

    @Test
    public void deleteContactById(){
        service.deleteContactById(ContactData.ID);
    }

}
