import java.math.BigInteger;

/**
 * App to calculate the factorial of a random number.
 * Comparing of sequential and async. parallel execution.
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.5
 */
public class FactorialApp {
    public static void main(String[] args) {

        FactorialAlgorithm factorial = new FactorialAlgorithm();

        int factorialNumber = randomNumberForFactorial(80000, 100000);

        System.out.println("Factorial Number: " + factorialNumber);

        // Sequential execution
        long timeStart = System.nanoTime();
        BigInteger resultSingle = factorial.multiplyFromAToB(0, factorialNumber);
        long timeEnd = System.nanoTime();
        long timeNeeded = timeEnd - timeStart;
        System.out.println("Result         : " + resultSingle);
        System.out.println("calculated in " + (timeNeeded / 1000) + " ms");

        // Parallel execution
        long timeStartFuture = System.nanoTime();
        BigInteger resultFuture = factorial.factorialOfANumber(factorialNumber);
        long timeEndFuture = System.nanoTime();
        long timeNeededFuture = timeEndFuture - timeStartFuture;

        System.out.println("Result (Future): " + resultFuture);
        System.out.println("calculated in " + (timeNeededFuture / 1000) + " ms");
    }

    /**
     * Calculate a random number for a specific range
     *
     * @param min range start number
     * @param max range end number
     * @return random number
     */
    private static int randomNumberForFactorial(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }
}
