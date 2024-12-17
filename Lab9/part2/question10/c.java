import java.util.IntSummaryStatistics;
import java.util.stream.Stream;

public class c {
    public static void main(String[] args) {
        Stream<Integer> intger = Stream.of(12, 5, 8, 6, 8);
        IntSummaryStatistics intState = intger.mapToInt(Integer::intValue).summaryStatistics();

        System.out.println(intState.getMax());
        System.out.println(intState.getMin());
    }
}
