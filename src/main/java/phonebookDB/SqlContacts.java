package phonebookDB;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlContacts implements Control {
    private Connection cn;

    public SqlContacts() {
    }

    public SqlContacts(Connection cn) {
        this.cn = cn;
    }

    public void init() {
        try (InputStream in = SqlContacts.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("admin")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Contact add(Contact contact) {
        try (PreparedStatement preparedStatement = cn.prepareStatement(
                "insert into phonebook.contacts(firstName, lastName, email) values (?, ?, ?) ", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, contact.getFirstName());
            preparedStatement.setString(2, contact.getLastName());
            preparedStatement.setString(3, contact.getEmail());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    contact.setId(generatedKeys.getInt(1));
                    contact.getNumbers().get(0).setContactId(contact.getId());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (PreparedStatement preparedStatement = cn.prepareStatement(
                "insert into phonebook.numbers(contactId, number) values (?, ?) ", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, contact.getId());
            preparedStatement.setLong(2, contact.getNumbers().get(0).getNumber());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    contact.getNumbers().get(0).setId(generatedKeys.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contact;
    }

    @Override
    public List<Contact> findAll() {
        List<Contact> contacts = new ArrayList<>();
        try (PreparedStatement preparedStatement = cn.prepareStatement(
                "select * from phonebook.contacts c, phonebook.numbers n where n.contactId = c.id and n.id = 1")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    contacts.add(new Contact(
                            resultSet.getString("firstName"),
                            resultSet.getString("lastName"),
                            resultSet.getString("email"),
                            resultSet.getLong("number")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contacts;
    }

    @Override
    public List<Contact> findByFirstName(String name) {
        List<Contact> contacts = new ArrayList<>();
        try (PreparedStatement preparedStatement = cn.prepareStatement(
                "select * from phonebook.contacts c where c.firstName = ?")) {
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    contacts.add(new Contact(
                            resultSet.getString("firstName"),
                            resultSet.getString("lastName"),
                            resultSet.getString("email"),
                            resultSet.getLong("number")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contacts;
    }

    @Override
    public List<Contact> findByLastName(String name) {
        List<Contact> contacts = new ArrayList<>();
        try (PreparedStatement preparedStatement = cn.prepareStatement(
                "select * from phonebook.contacts c where c.lastName = ?")) {
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    contacts.add(new Contact(
                            resultSet.getString("firstName"),
                            resultSet.getString("lastName"),
                            resultSet.getString("email"),
                            resultSet.getLong("number")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contacts;
    }

    @Override
    public List<Contact> findByFirstAndLastName(String firstName, String lastName) {
        List<Contact> contacts = new ArrayList<>();
        try (PreparedStatement preparedStatement = cn.prepareStatement(
                "select * from phonebook.contacts c where c.firstName = ? and c.lastName = ?")) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    contacts.add(new Contact(
                            resultSet.getString("firstName"),
                            resultSet.getString("lastName"),
                            resultSet.getString("email"),
                            resultSet.getLong("number")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contacts;
    }

    @Override
    public Contact findByNumber(Long number) {
        Contact contact = null;
        try (PreparedStatement preparedStatement = cn.prepareStatement(
                "select c.firstName, c.lastName, c.email from phonebook.numbers n, phonebook.contacts c " +
                        "where n.number = ? and c.id = n.contactId")) {
            preparedStatement.setLong(1, number);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    contact = new Contact(
                            resultSet.getString("firstName"),
                            resultSet.getString("lastName"),
                            resultSet.getString("email"),
                            number
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contact;
    }

    @Override
    public List<Long> findAllNumbersByNumber(Long number) {
        List<Long> numbers = new ArrayList<>();
        try (PreparedStatement preparedStatement = cn.prepareStatement(
                "select n.number from phonebook.numbers n where n.number = ?")) {
            preparedStatement.setLong(1, number);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    numbers.add(resultSet.getLong("number"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numbers;
    }

    @Override
    public Boolean deleteByNumber(Long number) {
        boolean result = false;
        try (PreparedStatement preparedStatement = cn.prepareStatement(
                "delete from phonebook.contacts c where c.id = (select n.contactId " +
                        "from phonebook.numbers n where n = ?")) {
            preparedStatement.setLong(1, number);
            result = preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static void main(String[] args) {
        Output out = new ConsoleOutput();
        Input validate = new ValidateInput(out, new ConsoleInput());
        try (Control contacts = new SqlContacts()) {
            contacts.init();
            new MainUI(out).init(validate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }
}