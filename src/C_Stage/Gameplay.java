package C_Stage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Miguel Emmara
 */
public class Gameplay {
    private static final String[] winners_name = new String[3];
    private static final String[] winners_medal = new String[3];
    private static final double[] winners_score = new double[3];
    private static final int ROUND = 3;
    private static final String[] PRE_MADE_PLAYER_NAMES = {"Miguel", "Jacob", "Mahela", "Amos", "Aidan",
            "Rahul", "Lucas", "Bob", "Anthony", "Fred", "Chester", "Saul", "Cory", "Josiah", "Jerry", "Steven"};
    private static Competitors[] competitors;

    public Gameplay() {
        competitors = new Competitors[0];
    }

    public void start(Scanner scanner) {
        boolean stop = false;
        int number_competitors = 0;

        do {
            try {
                System.out.print("\nPlease enter the competitors number (12 to 16): ");
                number_competitors = scanner.nextInt();
                scanner.nextLine();

                if (number_competitors <= 11 || number_competitors >= 17) {
                    System.out.println("Please Input Between 12 - 16");
                } else {
                    stop = true;
                }

            } catch (InputMismatchException e) {
                System.out.println("Please Input valid Number");
                scanner.nextLine();
            }
        } while (!stop);

        stop = false;

        competitors = new Competitors[number_competitors];

        do {
            try {
                System.out.print("Would you like to use pre entered names? Yes (y) No (n): ");
                String str = scanner.nextLine().toLowerCase();

                switch (str) {
                    case "y":
                        for (int i = 0; i < competitors.length; i++) {
                            competitors[i] = new Competitors();
                            competitors[i].setPlayer_name(PRE_MADE_PLAYER_NAMES[i]);
                        }
                        stop = true;
                        break;
                    case "n":
                        System.out.println("Please enter " + number_competitors + " competitor's name:\n");
                        int index = 1;
                        for (int i = 0; i < competitors.length; i++) {
                            competitors[i] = new Competitors();
                            System.out.print("Name " + index + ": ");
                            competitors[i].setPlayer_name(scanner.nextLine());
                            index++;
                        }
                        stop = true;
                        break;

                    default:
                        throw new IllegalStateException("Unexpected value: " + str);
                }
            } catch (IllegalStateException e) {
                System.out.println("Please Input Correct Answer");
            }
        } while (!stop);

        System.out.println("\nCompetitor's Names:\n" +
                "==============\n");

        int counter = 1;
        for (Competitors value : competitors) {
            System.out.println("Competitor " + counter + ": " + value.getPlayer_name());
            counter++;
        }

        simulation(scanner, competitors);
    }

    public void simulation(Scanner scanner, Competitors[] competitors) {
        Random random = new Random();
        double randomValue;
        DecimalFormat df = new DecimalFormat("#.00");
        String dp2;
        boolean stop = false;

        int index = 1;
        while (index <= ROUND) {
            System.out.print("\nPlease press Enter to start the round " + index + ": ");
            scanner.nextLine();

            System.out.println("\n# Round " + index + " Begin #");
            System.out.println("\nThrows");
            System.out.println("==============\n");

            for (Competitors competitor : competitors) {
                randomValue = 40 + (70 - 40) * random.nextDouble();

                if (randomValue > competitor.getPlayer_best_score()) {
                    competitor.setPlayer_best_score(randomValue);
                }

                competitor.setPlayer_score(randomValue);
                dp2 = df.format(competitor.getPlayer_score());

                System.out.printf("%-8s Threw %8s", competitor.getPlayer_name(), dp2);
                System.out.println();
            }

            System.out.println("\nBest Throws");
            System.out.println("==============\n");

            for (Competitors competitor : competitors) {
                dp2 = df.format(competitor.getPlayer_best_score());

                System.out.printf("%-8s Best Throw %8s", competitor.getPlayer_name(), dp2);
                System.out.println();
            }

            System.out.println("\n# Round " + index + " End #");
            index++;
        }

        do {
            try {
                System.out.print("\nWould you like to see the winners? (y/n): ");
                String str = scanner.nextLine().toLowerCase();

                switch (str) {
                    case "y":
                        winners(competitors);
                        stop = true;
                        break;
                    case "n":
                        System.out.println("Thank you for using our app.");
                        stop = true;
                        break;

                    default:
                        throw new IllegalStateException("Unexpected value: " + str);

                }
            } catch (IllegalStateException e) {
                System.out.println("Please Input Correct Answer");
            }

        } while (!stop);
    }

    public void winners(Competitors[] competitors) {
        DecimalFormat df = new DecimalFormat("#.00");
        BigDecimal bd;
        double bronze;
        double silver;
        double gold;

        bronze = gold = silver = Integer.MIN_VALUE;
        for (Competitors competitor : competitors) {
            bd = BigDecimal.valueOf(competitor.getPlayer_best_score()).setScale(2, RoundingMode.HALF_UP);
            double dp2 = bd.doubleValue();
            /* If current element is greater than
            gold*/
            if (dp2 > gold) {
                bronze = silver;
                silver = gold;
                gold = dp2;
                winners_name[0] = competitor.getPlayer_name();
            }

            /* If arr[i] is in between gold and
            silver then update silver  */
            else if (dp2 > silver) {
                bronze = silver;
                silver = dp2;
                winners_name[1] = competitor.getPlayer_name();
            } else if (dp2 > bronze) {
                bronze = dp2;
                winners_name[2] = competitor.getPlayer_name();
            }

            winners_medal[0] = "Gold";
            winners_score[0] = gold;


            winners_medal[1] = "Silver";
            winners_score[1] = silver;


            winners_medal[2] = "Bronze";
            winners_score[2] = bronze;
        }

        printWinner(df);
    }

    private void printWinner(DecimalFormat df) {
        System.out.println("\nWINNERS OF 2021 DISCUS EVENT FOR MEN COMPETITION RESULTS:\n" +
                "=========================================================\n");

        for (int i = 0; i < ROUND; i++) {
            System.out.printf("%-8s Won a %-7s Medal With A Best Throw Of %5s Metres", winners_name[i], winners_medal[i],
                    df.format(winners_score[i]));
            System.out.println();
        }

        System.out.println("\nCongratulations to the winners!");
        System.out.println("Thank you for using our app.");
    }
}
