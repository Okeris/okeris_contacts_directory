package phonebookDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Contact {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Number> numbers;

    public Contact(String firstName, String lastName, String email, Long number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        List<Number> list = new ArrayList<>();
        Number num = new Number(number);
        System.out.printf(num.toString());
        if (list != null) {
            list.add(num);
        } else {
            System.out.println("non");
        }
        this.numbers = list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public List<Number> getNumbers() {
        return numbers;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumbers(List<Number> numbers) {
        this.numbers = numbers;
    }

    public String toStringHead() {
        StringBuilder sb = new StringBuilder("");;
        sb.append(firstName).append(" ").append(lastName).append(numbers.get(0));
        return String.valueOf(sb);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        StringBuilder nums = new StringBuilder("");;
        numbers.forEach(number -> nums.append(number).append(System.lineSeparator()));
        sb.append("Contact:").append(System.lineSeparator()).append(firstName).append(" ").append(lastName)
                .append(System.lineSeparator()).append("email = ").append(email).append(System.lineSeparator())
                .append(nums);
        return String.valueOf(sb);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(firstName, contact.firstName) && Objects.equals(lastName, contact.lastName) && Objects.equals(email, contact.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email);
    }
}