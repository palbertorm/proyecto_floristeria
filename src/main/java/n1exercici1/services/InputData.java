package n1exercici1.services;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputData {

    public static int askInt(String message) {
        Scanner input = new Scanner (System.in);
        int number = 0;
        boolean correct = false;
        do {
            System.out.print(message);
            try {
                number = input.nextInt();
                correct = true;
            } catch (InputMismatchException ex) {
                System.out.println("Format error. Enter an integer");
            }
            input.nextLine();
        } while (!correct);
        return number;
    }
    public static String askString (String message) {
        Scanner input = new Scanner (System.in);
        System.out.print(message);
        return input.nextLine();
    }

}
