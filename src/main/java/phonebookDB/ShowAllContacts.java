package phonebookDB;


import java.util.List;

public class ShowAllContacts implements Action {
    private final Output out;

    public ShowAllContacts(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Show all contacts.";
    }

    @Override
    public boolean execute(Input input, Control contacts) {
        out.println("==== Show all contacts ====");
        try {
            List<Contact> contactsList = contacts.findAll();
            for (Contact it : contactsList) {
                out.println(it);
            }
        } catch (NullPointerException e) {
            System.out.println("Contacts is empty");
        }
        return true;
    }
}