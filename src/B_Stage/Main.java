package B_Stage;

import java.util.Scanner;

/**
 * Created by Miguel Emmara
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean stop;
        welcome();
        Gameplay game = new Gameplay();
        game.start(scanner);

        do {
            try {
                System.out.print("\nWould you like to run another simulation? (y/n): ");
                String answer = scanner.nextLine().toLowerCase();

                switch (answer) {
                    case "y":
                        game.start(scanner);
                        stop = false;
                        break;
                    case "n":
                        System.out.println("Thank you for using our app.");
                        stop = true;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + answer);
                }
            } catch (IllegalStateException e) {
                System.out.println("Please Input Correct Answer");
                stop = false;
            }
        } while (!stop);
    }

    private static void welcome() {
        System.out.println("2021 Discus Event for Men Competition\n" +
                "=====================================");
    }
}
