package phonebookDB;

public class NewContact implements Action {
    private final Output out;

    public NewContact(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "New contact.";
    }

    @Override
    public boolean execute(Input input, Control contacts) {
        out.println("=== Create a new contact ====");
        String firstName = input.inStr("Enter firstName: ");
        String lastName = input.inStr("Enter lastName: ");
        String email = input.inStr("Enter email: ");
        Long number = input.inLong("Enter number: ");
//        Contact contact = ;
//        System.out.println("================");
//        System.out.println(contact);
//        System.out.println("================");
//        System.out.println(contact.getFirstName());
//        System.out.println(contact.getLastName());
//        System.out.println(contact.getEmail());
//        System.out.println(contact.getNumbers().get(0));
//        System.out.println("================");
//        System.out.println(contact.toString());
        contacts.add(new Contact(firstName, lastName, email, number));
        return true;
    }
}