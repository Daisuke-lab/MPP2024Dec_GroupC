import java.util.List;
import java.util.stream.Collectors;



public class LambdaLibrary {
     public static final TriFunction<List<Employee>, Integer, Character, String> EMPLOYEES_WITH_HIGH_SALARY_AND_NAME_RANGE =
            (list, salary, startLetter) -> list.stream()
                    .filter(e -> e.getSalary() > salary)                // Salary filter
                    .filter(e -> e.getLastName().charAt(0) >= startLetter) // Last name range filter
                    .map(Employee::fullName)                           // Map to full name
                    .sorted()                                          // Sort alphabetically
                    .collect(Collectors.joining(", ")); 
}
