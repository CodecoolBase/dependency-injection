package hu.codecool.contact.impl;

import hu.codecool.contact.api.ContactStore;
import hu.codecool.contact.model.Contact;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class CSVContactStore implements ContactStore {

    private final Path path;

    public CSVContactStore(Path path) {
        this.path = path;
    }

    @Override
    public List<Contact> getAll() {
        try {
            List<Contact> result = new ArrayList<>();
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            for (String line : lines) {
                if ("".equals(line.trim())) {
                    continue;
                }
                String[] fields = line.split(",", 3);
                result.add(new Contact(fields[0], fields[1], fields[2]));
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
