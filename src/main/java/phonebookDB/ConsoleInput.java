package phonebookDB;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String inStr(String str) {
        System.out.print(str);
        return scanner.nextLine();
    }

    @Override
    public Long inLong(String str) {
        return Long.valueOf(inStr(str));
    }
}