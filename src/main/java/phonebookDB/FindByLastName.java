package phonebookDB;

import java.util.List;

public class FindByLastName implements Action {
    private final Output out;

    public FindByLastName(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Find contacts by lastname.";
    }

    @Override
    public boolean execute(Input input, Control contacts) {
        out.println("==== Find contacts by lastname ====");
        String name = input.inStr("Enter lastname: ");
        List<Contact> contactsList = contacts.findByLastName(name);
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