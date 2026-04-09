package com.easybank.digitalbanking.controller;

import com.easybank.digitalbanking.model.Contact;
import com.easybank.digitalbanking.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/easybank")
@RequiredArgsConstructor
public class ContactController {
    private final ContactRepository contactRepository;

    @PostMapping("/contactus")
    // @PreFilter("filterObject.contactName != 'Test'")
    @PostFilter("filterObject.contactName != 'Test'")
    public List<Contact> saveContactInquiryDetails(@RequestBody List<Contact> contacts) {
        List<Contact> returnContacts = new ArrayList<>();
        if(!contacts.isEmpty()) {
            Contact contact = contacts.getFirst();
            contact.setContactId(getServiceReqNumber());
            contact.setCreateDt(new Date(System.currentTimeMillis()));
            Contact savedContact = contactRepository.save(contact);
            returnContacts.add(savedContact);
        }
        return returnContacts;
    }
    public String getServiceReqNumber() {
        Random random = new Random();
        int ranNum = random.nextInt(999999999 - 9999) + 9999;
        return "SR" + ranNum;
    }
}
