package phonebookDB;

public interface Action {
    String name();

    boolean execute(Input input, Control contracts);
}
