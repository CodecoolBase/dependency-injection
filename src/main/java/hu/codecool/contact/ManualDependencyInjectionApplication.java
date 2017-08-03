package hu.codecool.contact;

import hu.codecool.contact.api.ContactManager;
import hu.codecool.contact.api.ContactStore;
import hu.codecool.contact.impl.CSVContactStore;
import hu.codecool.contact.impl.SimpleContactManager;
import hu.codecool.contact.model.Contact;

import java.nio.file.Paths;
import java.util.Scanner;

public class ManualDependencyInjectionApplication {

    public static void main(String[] args) {
        String profile = System.getProperty("hu.codecool.contact.ManualDependencyInjectionApplication.profile", "development");

        // Manual dependency discovery happening here.
        ContactStore contactStore;
        if ("development".equals(profile)) {
            String path = System.getProperty("hu.codecool.contact.impl.CSVContactStore.path", "contacts.csv");
            contactStore = new CSVContactStore(Paths.get(path));
        } else {
            throw new RuntimeException("Unsatisfied dependency " + ContactStore.class.getName());
        }

        // Manual dependency injection happening here.
        ContactManager contactManager = new SimpleContactManager(contactStore);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a query and press <Enter> to search for a contact: ");
            String query = scanner.nextLine();
            Contact contact = contactManager.findFirst(query);
            if (contact == null) {
                System.out.format("No contact found mathing: %s%n", query);
            } else {
                System.out.format("Found: %s:%n", contact);
            }
        }
    }
}
