package hu.codecool.contact.api;

import hu.codecool.contact.model.Contact;

import java.util.List;

public interface ContactManager {

    Contact findFirst(String query);

    List<Contact> findAll(String query);
}
