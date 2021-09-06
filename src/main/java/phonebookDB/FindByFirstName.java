package phonebookDB;

import java.util.List;

public class FindByFirstName implements Action {
    private final Output out;

    public FindByFirstName(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Find contacts by name.";
    }

    @Override
    public boolean execute(Input input, Control contacts) {
        out.println("==== Find contacts by name ====");
        String name = input.inStr("Enter name: ");
        List<Contact> contactsList = contacts.findByFirstName(name);
        if (contactsList.size() > 0) {
            for (Contact con : contactsList) {
                out.println(con.toStringHead());
            }
        } else {
            out.println("phonebookDB.Contacts with that lastname not found.");
        }
        return true;
    }
}
