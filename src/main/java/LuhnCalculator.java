package main.java;

public class LuhnCalculator {

    private static int expectedControlNr;
    private static boolean isCreditCard, isValid;

    public boolean checkLuhn(String cardNumber, boolean calculateControlNr) {

        cardNumber = cardNumber.replaceAll(" ", "");

        if (calculateControlNr) {                                           // Condition for removing the last digit from the
                                                                            // input string when calculating expected check digit.
            cardNumber = cardNumber.substring(0, cardNumber.length() - 1);
        }
        int numberOfDigits = cardNumber.length();

        if ((numberOfDigits == 16 && !calculateControlNr) || (numberOfDigits == 15 && calculateControlNr)) {        // Conditions to only change the bool isCreditcard on the first run
            isCreditCard = true;                                                                                    // and not on second run when we pass in a shorter String for
        } else {                                                                                                    // only calculating expected check digit.
            isCreditCard = false;
        }

        int sum = 0;
        boolean isSecond = calculateControlNr;

        for (int i = numberOfDigits - 1; i >= 0; i--) {

            int digit = cardNumber.charAt(i) - '0';

            if (isSecond)
                digit = digit * 2;

            sum += digit / 10;      // Conditions for when digit becomes a double digit number(adding first and second digit to sum as separate integers
            sum += digit % 10;      //

            isSecond = !isSecond;
        }

        if (calculateControlNr) {
            expectedControlNr = (10 - (sum % 10)) % 10;   //added extra modulus operation for the case if expectedCheckNr becomes double digit. which happens when provided control digit 0 ( 10-0 = 10)
            InputOutputManager.print("Expected: " + expectedControlNr);

        } else {
            isValid = (sum % 10 == 0);
        }
        return (sum % 10 == 0);
    }

    public static int getExpectedControlNr() { return expectedControlNr; }
    public static boolean getIsCreditCard() { return isCreditCard; }
    public static boolean getIsValid() { return isValid; }
}