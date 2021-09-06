package phonebookDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Contacts {
    private final List<Contact> contacts = new ArrayList<>();

    public Contact add(Contact contact) {
        contacts.add(contact);
        return contact;
    }

    public List<Contact> findAll() {
        return contacts;
    }

    public List<Contact> findByFirstName(String name) {
        List<Contact> contactsN = new ArrayList<>();
        for (Contact i : contacts) {
            if (i.getFirstName().equals(name)) {
                contactsN.add(i);
            }
        }
        return contactsN;
    }

    public List<Contact> findByLastName(String name) {
        List<Contact> contactsN = new ArrayList<>();
        for (Contact i : contacts) {
            if (i.getLastName().equals(name)) {
                contactsN.add(i);
            }
        }
        return contactsN;
    }

    public List<Contact> findByFirstAndLastName(String firstName, String lastName) {
        List<Contact> contactsN = new ArrayList<>();
        for (Contact i : contacts) {
            if (i.getFirstName().equals(firstName)) {
                if (i.getLastName().equals(lastName)) {
                    contactsN.add(i);
                }
            }
        }
        return contactsN;
    }

    public List<Long> findAllNumbersByNumber(Long number) {
        for (Contact c : contacts) {
            List<Number> nums = c.getNumbers();
            if (nums.stream().allMatch(n -> Objects.equals(n.getNumber(), number))) {
                return nums.stream().map(n -> Long.parseLong(String.valueOf(n))).collect(Collectors.toList());
            }

        }
        return null;
    }

    public Contact findByNumber(Long number) {
        for (Contact c : contacts) {
            List<Number> nums = c.getNumbers();
            if (nums.stream().allMatch(n -> Objects.equals(n.getNumber(), number))) {
                return c;
            }

        }
        return null;
    }

    public boolean deleteByNumber(Long number) {
        contacts.remove(findByNumber(number));
        return true;
    }
}
