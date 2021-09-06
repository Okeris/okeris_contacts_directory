package phonebookDB;

import java.util.List;

public class FindAllNumbersByNumber implements Action{
    private final Output out;

    public FindAllNumbersByNumber(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Find all contact numbers by main number.";
    }

    @Override
    public boolean execute(Input input, Control contacts) {
        out.println("==== Find contact's numbers by main number ====");
        Long number = input.inLong("Enter number: ");
        List<Long> numbers = contacts.findAllNumbersByNumber(number);
        if (numbers != null) {
            numbers.forEach(System.out::println);
        } else {
            out.println("Contact with that number not found.");
        }
        return true;
    }
}