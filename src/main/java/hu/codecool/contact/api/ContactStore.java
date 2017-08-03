package hu.codecool.contact.api;

import hu.codecool.contact.model.Contact;

import java.util.List;

public interface ContactStore {

    List<Contact> getAll();
}
