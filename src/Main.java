import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

            Scanner scanner = new Scanner(System.in);
            Case cs = new Case();

            while (true) {
                cs.menu();
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {

                    case 1:
                        cs.withdrawn();
                        break;
                    case 2:
                        cs.deposit();
                        break;
                    case 3:
                       cs.balance();
                        break;
                    case 4:
                       cs.createUser();
                        break;
                    case 5:
                        cs.createCard();
                        break;
                    case 6:
                        System.exit(0);
                }
            }
         }
    }

