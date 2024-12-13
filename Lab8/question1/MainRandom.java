import java.util.function.Supplier;
public class MainRandom{
    
    public static void main(String[] args) {
        class RandomSupplier implements Supplier<Double> {
            @Override
            public Double get() {
                return Math.random();
            }
        }
        Supplier<Double> randomSupplierInnerClass = new RandomSupplier();
        System.out.println("Random number (inner class): " + randomSupplierInnerClass.get());
    }
}