package phonebookDB;

public class ExitContacts implements Action{
    @Override
    public String name() {
        return "Exit.";
    }


    @Override
    public boolean execute(Input input, Control contacts) {
        return false;
    }
}
