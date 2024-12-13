import java.util.*;
import java.util.function.BiFunction;

public class BiFunc {



    public static void main(String[] args) {
        
        // BiFunction lambda expression
        BiFunction<Double, Double, List<Double>> powerAndProduct = (x, y) -> {
            List<Double> list = new ArrayList<>();
            list.add(Math.pow(x, y));
            list.add(x * y);
            return list;
        };

        System.out.println(powerAndProduct.apply(2.0,3.0));
    }
}
