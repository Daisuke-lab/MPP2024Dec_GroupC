import java.util.stream.Collectors;
import java.util.stream.Stream;

public class b {
    public static void main(String[] args) {

        Stream<String> stringStream = Stream.of("Bill", "Thomas", "Mary");
        String joinedStream = stringStream.collect(Collectors.joining(", "));
        System.out.println(joinedStream);

    }
}
