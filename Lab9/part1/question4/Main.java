package lesson9.labs.prob4;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class Main {
    // Supplier to generate a new infinite primes stream
    private final Supplier<Stream<Integer>> primeSupplier = () -> Stream.iterate(2, Main::nextPrime);

    public static void main(String[] args) {

        final Stream<Integer> primes = Stream.iterate(2, Main::nextPrime);

        /*To begin, create a final variable Stream<Integer> primes that contains all prime
        numbers (in particular, the Stream is infinite). Generate the primes using the iterate
        method of Stream – do not use the map or filter Stream operations.*/
        primes.limit(10).forEach(System.out::println);

        /*Next, create a variation of the primes Stream that can be called multiple times by a
        method printFirstNPrimes(long n), which prints to the console the first n prime
        numbers. Note that the Stream primes that you created in part A cannot be used a
        second time; how can you get around that limitation? Prove that you succeeded by calling
        the method printFirstNPrimes(long n) (from a main method) more than once.*/
        Main ps = new Main();
        ps.printFirstNPrimes(10); // Print the first 10 primes
        System.out.println("====");
        ps.printFirstNPrimes(5);
    }

    // Method to find the next prime number
    private static int nextPrime(int current) {
        int next = current + 1;
        while (!isPrime(next)) {
            next++;
        }
        return next;
    }

    // Helper method to check if a number is prime
    private static boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    // Method to print the first n primes
    public void printFirstNPrimes(long n) {
        primeSupplier.get() // Generate a new stream
                .limit(n)       // Limit to the first n primes
                .forEach(System.out::println); // Print each prime
    }

}
