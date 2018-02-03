import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class for testing the "FactorialAlgorithm" class and some cases
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.5
 */
class FactorialAlgorithmTest {

    private FactorialAlgorithm factorial;

    @BeforeEach
    void setUp() {
        factorial = new FactorialAlgorithm(10);
    }

    /**
     * Simple factorial from 9 to 12
     */
    @Test
    void multiplyFromAToB() {
        assertEquals(BigInteger.valueOf(11880),
                factorial.multiplyFromAToB(9, 12));
    }

    /**
     * Simple factorial of 1
     * Testing for the special case of 1
     */
    @Test
    void multiplyFromOneToOne() {
        assertEquals(BigInteger.valueOf(1),
                factorial.multiplyFromAToB(1, 1));
    }

    /**
     * Async. factorial calculation for 7
     * Testing for an uneven and small number
     */
    @Test
    void multiplyToASpecificNumber() {
        BigInteger result = BigInteger.valueOf(5040);
        assertEquals(result,
                factorial.factorialOfANumber(7));
    }
}