package phonebookDB;

public class DeleteByNumber implements Action{
    private final Output out;

    public DeleteByNumber(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Delete contact.";
    }

    @Override
    public boolean execute(Input input, Control contacts) {
        out.println("==== Delete contact ====");
        Long number = input.inLong("Enter number: ");
        if (contacts.deleteByNumber(number)) {
            out.println("phonebookDB.Contact was deleted.");
        } else {
            out.println("phonebookDB.Contact with that number not found.");
        }
        return true;
    }
}
