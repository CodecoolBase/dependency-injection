package hu.codecool.contact;

import hu.codecool.contact.api.ContactStore;
import hu.codecool.contact.model.Contact;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// Used for testing only.
public final class FakeContactStore implements ContactStore {

    private final List<Contact> contacts;

    public FakeContactStore(Contact... contacts) {
        this(Arrays.stream(contacts).collect(Collectors.toList()));
    }

    public FakeContactStore(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public List<Contact> getAll() {
        return contacts;
    }
}
