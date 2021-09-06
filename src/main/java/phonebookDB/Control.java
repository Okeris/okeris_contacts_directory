package phonebookDB;

import java.util.List;

public interface Control extends AutoCloseable{
    void init();
    Contact add(Contact contact);
    List<Contact> findAll();
    List<Contact> findByFirstName(String name);
    List<Contact> findByLastName(String name);
    List<Contact> findByFirstAndLastName(String firstName, String lastName);
    List<Long> findAllNumbersByNumber(Long number);
    Contact findByNumber(Long number);
    Boolean deleteByNumber(Long number);
}