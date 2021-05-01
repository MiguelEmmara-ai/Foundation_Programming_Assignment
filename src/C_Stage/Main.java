package C_Stage;

import java.util.Scanner;

/**
 * Created by Miguel Emmara
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        welcome();
        Gameplay game = new Gameplay();
        game.start(scanner);
    }

    private static void welcome() {
        System.out.println("2021 Discus Event for Men Competition\n" +
                "=====================================");
    }
}
