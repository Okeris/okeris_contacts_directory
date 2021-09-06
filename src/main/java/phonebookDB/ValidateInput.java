package phonebookDB;

public class ValidateInput implements Input{
    private final Output out;
    private final Input in;

    public ValidateInput(Output out, Input input) {
        this.out = out;
        this.in = input;
    }

    @Override
    public String inStr(String question) {
        return in.inStr(question);
    }

    @Override
    public Long inLong(String question) {
        boolean invalid = true;
        Long value = -1L;
        do {
            try {
                value = in.inLong(question);
                invalid = false;
            } catch (NumberFormatException nfe) {
                out.println("Please enter validate data again.");
            }
        } while (invalid);
        return value;
    }
}