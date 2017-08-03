package hu.codecool.contact;

import hu.codecool.contact.api.ContactStore;
import hu.codecool.contact.impl.SimpleContactManager;
import hu.codecool.contact.model.Contact;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class SimpleContactManagerTest {

    public static final Contact CONTACT1 = new Contact("aaa", "ccc", "12345");
    public static final Contact CONTACT2 = new Contact("aaa", "ddd", "23456");
    public static final Contact CONTACT3 = new Contact("bbb", "eee", "34567");

    // Subject under test.
    private SimpleContactManager contactManager;

    @Before
    public void before() {
        ContactStore contactStore = new FakeContactStore(
            CONTACT1,
            CONTACT2,
            CONTACT3);

        contactManager = new SimpleContactManager(contactStore);
    }

    @Test
    public void testFindFirstByFullFirstName() {
        // when
        Contact c = contactManager.findFirst("aaa");

        // then
        Assert.assertEquals(c, CONTACT1);
    }

    @Test
    public void testFindFirstByFullLastName() {
        // when
        Contact c = contactManager.findFirst("ddd");

        // then
        Assert.assertEquals(c, CONTACT2);
    }

    @Test
    public void testFindFirstByPartialLasttName() {
        // when
        Contact c = contactManager.findFirst("e");

        // then
        Assert.assertEquals(c, CONTACT3);
    }

    @Test
    public void testFindFirstByFullPhoneNumber() {
        // when
        Contact c = contactManager.findFirst("12345");

        // then
        Assert.assertEquals(c, CONTACT1);
    }

    @Test
    public void testFindAllByFullFisrtName() {
        // when
        List<Contact> contacts = contactManager.findAll("aaa");

        // then
        Assert.assertEquals(contacts.size(), 2);
        Assert.assertTrue(contacts.contains(CONTACT1));
        Assert.assertTrue(contacts.contains(CONTACT2));
    }
}
