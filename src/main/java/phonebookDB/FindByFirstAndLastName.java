package phonebookDB;

import java.util.List;

public class FindByFirstAndLastName implements Action {
    private final Output out;

    public FindByFirstAndLastName(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Find contacts by name and lastname.";
    }

    @Override
    public boolean execute(Input input, Control contacts) {
        out.println("==== Find contacts by name and lastname ====");
        String name = input.inStr("Enter name: ");
        String lastname = input.inStr("Enter lastname: ");
        List<Contact> contactsList = contacts.findByFirstAndLastName(name, lastname);
        if (contactsList.size() > 0) {
            for (Contact con : contactsList) {
                out.println(con.toStringHead());
            }
        } else {
            out.println("phonebookDB.Contacts with that name not found.");
        }
        return true;
    }
}
