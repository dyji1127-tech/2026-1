package cse2010.hw3;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Fibonacci {
    /**
     * Iterative version of Fibonacci number generator.
     * 0, 1, 1, 2, 3, 5, 8, 13, 21, ...
     *
     * @param n the index of the Fibonacci number starting from 0
     * @return the n-th Fibonacci number
     */
    public static int fibIter(int n) {
        if (n <= 1)
            return n;

        int acc = 1;
        int prev = 0;

        while (n-- > 1) {
            int temp = acc;
            acc += prev;
            prev = temp;
        }
        return acc;
    }

    /**
     * Public API for tail-recursive version of the Fibonacci number generator.
     * 0, 1, 1, 2, 3, 5, 8, 13, 21, ...
     *
     * @param n the index of the Fibonacci number starting from 0
     * @return the n-th Fibonacci number
     */
    public static int fib(int n) {
        return fibTailRec(n, 1, 0);
    }

    /**
     * Tail-recursive version of Fibonacci number generator.
     * This method is private and should not be called directly.
     * Use {@link #fib(int)} instead.
     * 0, 1, 1, 2, 3, 5, 8, 13, 21, ...
     *
     * @param n       the index of the Fibonacci number starting from 0
     * @param current the current Fibonacci number
     * @param prev    the previous Fibonacci number
     */
    private static int fibTailRec(int n, int current, int prev) {
        if (n == 0) {
            return prev;
        }

        if (n == 1) {
            return current;
        }
        return fibTailRec(n-1, current, prev);
    }

    public static void main(String... args) {
        // These two print statements should print the same result.
        System.out.println(
                Arrays.toString(IntStream.rangeClosed(0, 20).map(Fibonacci::fibIter).toArray())
        );

        System.out.println(
                Arrays.toString(IntStream.rangeClosed(0, 20).map(Fibonacci::fib).toArray())
        );
    }
}

