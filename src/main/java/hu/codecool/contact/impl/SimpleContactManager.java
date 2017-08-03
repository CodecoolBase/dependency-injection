package hu.codecool.contact.impl;

import hu.codecool.contact.api.ContactManager;
import hu.codecool.contact.api.ContactStore;
import hu.codecool.contact.model.Contact;

import java.util.ArrayList;
import java.util.List;

public final class SimpleContactManager implements ContactManager {

    private final ContactStore contactStore;

    public SimpleContactManager(ContactStore contactStore) {
        this.contactStore = contactStore;
    }

    @Override
    public Contact findFirst(String query) {
        return findAll(query)
            .stream()
            .findFirst()
            .orElse(null);
    }

    @Override
    public List<Contact> findAll(final String query) {
        final List<Contact> result = new ArrayList<>();
        for (Contact contact : contactStore.getAll()) {
            boolean match = false;
            if (contact.getFirstName() != null) {
                match |= contact.getFirstName().contains(query);
            }
            if (contact.getLastName() != null) {
                match |= contact.getLastName().contains(query);
            }
            if (contact.getPhoneNumber() != null) {
                match |= contact.getPhoneNumber().contains(query);
            }
            if (match) {
                result.add(contact);
            }
        }
        return result;
    }
}
