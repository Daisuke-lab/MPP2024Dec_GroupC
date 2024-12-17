package lesson9.labs.prob9;

import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        printSquares(4);
    }

    public static void printSquares(int num) {
        Stream
                .iterate(1, Main::nextSquare).limit(num)
                .forEach(System.out::println);
    }


    public static int nextSquare(int n) {
        int sqrt = (int) Math.sqrt(n);
        return (int)Math.pow(sqrt + 1, 2);
    }
}
