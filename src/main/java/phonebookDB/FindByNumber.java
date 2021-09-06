package phonebookDB;

public class FindByNumber implements Action {
    private final Output out;

    public FindByNumber(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Find contacts by number.";
    }

    @Override
    public boolean execute(Input input, Control contacts) {
        out.println("==== Find contacts by number ====");
        Long number = input.inLong("Enter number: ");
        Contact contact = contacts.findByNumber(number);
        if (contact != null) {
            out.println(contact.toString());
        } else {
            out.println("phonebookDB.Contact with that number not found.");
        }
        return true;
    }
}