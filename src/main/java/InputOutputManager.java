package main.java;

import java.util.Scanner;

public class InputOutputManager {

    private static boolean doPrint = false;

    public static void inputAndOutput() {

        LuhnCalculator luhn = new LuhnCalculator();
        Scanner scanner = new Scanner(System.in);

        doPrint = true; //used to exclude print from @Test

        print("Insert a string of numbers:");

        String cardNumber = scanner.nextLine();
        scanner.close();
        try {
            long choiceNum = Long.parseLong(cardNumber.replaceAll(" ", ""));
            if (!cardNumber.contains(" "))
                cardNumber = cardNumber.substring(0, cardNumber.length() - 1) + ' ' + cardNumber.substring(cardNumber.length() - 1);

            print("\n" + "Input: " + cardNumber);
            print("Provided: " + cardNumber.charAt(cardNumber.length() - 1));

            luhn.checkLuhn(cardNumber, false);           //Validation of the String.
            luhn.checkLuhn(cardNumber, true);            //Calculation of control number.
        } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
            print("Invalid input, application will exit");
            System.exit(1);
        }
        if (LuhnCalculator.getIsValid())
            print("\n" + "Checksum: Valid");
        else
            print("\n" + "Checksum: Invalid");

        if (LuhnCalculator.getIsCreditCard()) {
            print("Digits: " + (cardNumber.length() - 1) + " + (credit card)");
        } else {
            print("Digits: " + (cardNumber.length() - 1));
        }
    }

    public static void print(String input) {
        if (doPrint)
            System.out.println(input);
    }
}
