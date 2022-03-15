package com.miniblog.poc.service.api.contact;

import com.miniblog.poc.api.ContactApi;
import com.miniblog.poc.api.model.*;
import com.miniblog.poc.service.service.ContactService;
import com.miniblog.poc.service.validator.contact.ContactSearchValidator;
import com.miniblog.poc.service.validator.contact.ContactValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/miniblog/backend/v1")
@CrossOrigin
@AllArgsConstructor
public class ContactController implements ContactApi {

    private final ContactService service;

    private final ContactValidator validator;

    private final ContactSearchValidator searchValidator;

    @Override
    public ResponseEntity<Contact> addContact(@RequestHeader(value="apikey") String apikey,
                                              @RequestBody ContactRequest request) {
        validator.validateAddContact(request);

        Contact contact = service.addContact(request);

        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Contact> getContactById(@RequestHeader(value= "apikey") String apikey,
                                                  @PathVariable("contactId") String id) {
        System.out.println(id);
        validator.validateContactExist(id);
        Contact contact = service.getContactById(id);

        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Contacts> getAllContacts(@RequestHeader(value="apikey") String apikey, Integer page, Integer pageSize) {
        Contacts contact = service.search(page, pageSize);

        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Contact> deleteContactById(@RequestHeader(value="apikey") String apikey,
                                                     @PathVariable("contactId") String id) {
        validator.validateContactExist(id);
        service.deleteContactById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
