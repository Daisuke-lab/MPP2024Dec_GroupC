import java.util.function.Supplier;
public class Main {
    public static void main(String[]args){
        //  Rewrite the method reference Math::random as a lambda expression
        // Supplier<Double> randomSupplier = () -> Math.random();
        // i 
        Supplier<Double> randomSupplier = () -> Math.random();

        System.out.println(randomSupplier.get());
        // ii
        Supplier<Double> rSupplier = Math::random;

        System.out.println(rSupplier.get());
        
    }
}