package phonebookDB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainUI {
    private final Output out;
    private final List<Action> actions;
    private Control sqlContacts;

    public MainUI(Output out) {
        this.out = out;
        this.actions = Collections.unmodifiableList(Arrays.asList(new NewContact(out), new ShowAllContacts(out), new FindByFirstName(out),
                new FindByLastName(out), new FindByFirstAndLastName(out), new FindByNumber(out),
                new DeleteByNumber(out), new ExitContacts()

        ));
        try {
            SqlContacts sqlContact = new SqlContacts();
            sqlContact.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init(Input input) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = Math.toIntExact(input.inLong("Enter action: "));
            Action action = this.actions.get(select);
            run = action.execute(input, sqlContacts);
        }
    }

    private void showMenu(List<Action> actions) {
        out.println("Menu.");
        for (Action act : actions) {
            out.println(actions.indexOf(act) + ". " + act.name());
        }
    }

    public static void main(String[] args) {
        Output output = new ConsoleOutput();
        Input input = new ValidateInput(output, new ConsoleInput());
        Contacts contacts = new Contacts();
        new MainUI(output).init(input);
    }
}
