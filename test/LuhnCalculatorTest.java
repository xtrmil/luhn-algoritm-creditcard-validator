import main.java.LuhnCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LuhnCalculatorTest {

    private final LuhnCalculator luhn = new LuhnCalculator();

    @Test
    public void checkIfNumbersIsValid() {                                        // Validating that inputnumbers arevalid creditcard numbers using valid credit card numbers.

        assertTrue(luhn.checkLuhn("536835147606859 0", false));
        assertTrue(luhn.checkLuhn("511127917506452 1", false));
        assertTrue(luhn.checkLuhn("510919323895415 2", false));
        assertTrue(luhn.checkLuhn("511485860336660 3", false));
        assertTrue(luhn.checkLuhn("557495454391488 4", false));
        assertTrue(luhn.checkLuhn("557495365306525 5", false));
        assertTrue(luhn.checkLuhn("557495374128661 6", false));
        assertTrue(luhn.checkLuhn("411824427209967 7", false));
        assertTrue(luhn.checkLuhn("557495963933436 8", false));
        assertTrue(luhn.checkLuhn("511064926204948 9", false));
    }

    @Test
    public void checkIfNumbersIsInvalid() {                                      // Validating that inputnumbers are invalid  using invalid credit card numbers.

        assertFalse(luhn.checkLuhn("123456789469436 6", false));
        assertFalse(luhn.checkLuhn("568567456345345 5", false));
        assertFalse(luhn.checkLuhn("679456356463463 3", false));
        assertFalse(luhn.checkLuhn("745645756833455 1", false));
        assertFalse(luhn.checkLuhn("967645676456456 0", false));
    }

    @Test
    public void checkIfExpectedDigitIsCorrect() {                                // Validating that expected control digit is valid using valid creditcard numbers.
        luhn.checkLuhn("411824427209967 7", true);
        assertEquals(7, luhn.getExpectedControlNr());
    }

    @Test
    public void checkIfExpectedDigitIsIncorrect() {                              // Validating that expected control digit is invalid using invalid creditcard numbers.
        luhn.checkLuhn("411824427209967 7", true);
        assertNotEquals(6, luhn.getExpectedControlNr());
        luhn.checkLuhn("568567456345345 5", true);
        assertNotEquals(3, luhn.getExpectedControlNr());
    }

    @Test
    public void checkIfValidAndCreditCard() {                                    // Validating that inputnumber is valid and that it has the correct credit card length.
        luhn.checkLuhn("411824427209967 7", false);
        assertTrue(luhn.getIsCreditCard() && luhn.getIsValid());
    }

    @Test
    public void checkIfInvalidCreditCard() {                                     // Validating that inputnumber is invalid and that it has the wrong credit card length.
        luhn.checkLuhn("58745390148585 3", false);
        assertFalse(luhn.getIsCreditCard() && luhn.getIsValid());
    }
}