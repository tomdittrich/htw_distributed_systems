import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Class to calculate asynchronous the factorial of a number
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.5
 */
public class FactorialAlgorithm {

    private int threadPoolSize;
    private ExecutorService executor;
    private List<FutureTask<BigInteger>> taskList;

    /**
     * Default constructor, with thread-pool-size of 10
     */
    public FactorialAlgorithm() {
        this(10);
    }

    /**
     * Constructor
     *
     * @param threadPoolSize thread-pool-size
     */
    public FactorialAlgorithm(int threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
        executor = Executors.newFixedThreadPool(threadPoolSize);
        taskList = new ArrayList<>();
    }

    /**
     * Calculates the factorial of a number.
     * Asynchronous calculation with future tasks.
     *
     * @param factorialNumber number to calculate
     * @return result
     */
    public BigInteger factorialOfANumber(int factorialNumber) {
        int divider = 10; // determined the amount of parallel tasks
        int remainder = 0;
        int factorialNumberDivisible = factorialNumber;

        // TODO: refactor, not the smartest solution
        // if factorialNumber is not smooth divisible without a remainder
        if ((factorialNumber % divider) != 0) {
            remainder = getFactorialDivisibleRest(factorialNumber, divider);

            factorialNumberDivisible -= remainder;

            runFactorialTask(factorialNumberDivisible + 1, factorialNumber); // to calculate the rest
        }

        int numbersPerThread = factorialNumberDivisible / divider;
        int firstNumber = 1;
        int lastNumber = numbersPerThread;

        for (int i = 1; i <= divider; i++) {
            runFactorialTask(firstNumber, lastNumber);

            firstNumber += numbersPerThread;
            lastNumber += numbersPerThread;
        }

        try {
            return multiplyFutureResults();
        } catch (Exception e) {
            e.printStackTrace();
            return BigInteger.ZERO;
        }
    }

    /**
     * If a factorial number is not smooth divisible,
     * this method calculates the rest.
     * E.g. 502 / 10 -> rest is 2
     *
     * @param factorialNumber number to calculate
     * @param divider divider
     * @return rest
     */
    private int getFactorialDivisibleRest(int factorialNumber, int divider) {
        int unevenNumbers = 0;

        while ((factorialNumber % divider) != 0) {
            factorialNumber--;
            unevenNumbers++;
        }

        return unevenNumbers;
    }

    /**
     * Start and add a factorial future task for a range of numbers
     *
     * @param firstNumber range start number
     * @param lastNumber range last number
     */
    private void runFactorialTask(int firstNumber, int lastNumber) {
        FutureTask<BigInteger> futureTask = new FutureTask<>(
                () -> (multiplyFromAToB(firstNumber, lastNumber)));
        taskList.add(futureTask);
        executor.execute(futureTask);
    }

    /**
     * Multiply the results of the future tasks
     *
     * @return final result
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private BigInteger multiplyFutureResults() throws ExecutionException, InterruptedException {
        BigInteger result = BigInteger.ONE;
        for (FutureTask<BigInteger> task : taskList) {

            result = result.multiply(task.get()); // BigInteger is immutable, so reassign
        }
        executor.shutdown();

        return result;
    }

    /**
     * Multiply the numbers within a range
     * (asynchronous)
     *
     * @param a range start number
     * @param b range last number
     * @return result
     */
    public BigInteger multiplyFromAToB(int a, int b) {
        BigInteger result = BigInteger.ONE;

        if (a == 0) a = 1;

        BigInteger aBig = BigInteger.valueOf(a);

        for (int i = a; i <= b; i++) {
            result = result.multiply(aBig); // BigInteger is immutable, so reassign
            aBig = aBig.add(BigInteger.ONE); // BigInteger is immutable, so reassign
        }

        return result;
    }
}
