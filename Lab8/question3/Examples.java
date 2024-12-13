
import java.util.function.*;

class Examples {
    
    
    // A. (Employee e) -> e.getName()
    Function<Employee, String> getName1 = (Employee e) -> e.getName();
    Function<Employee, String> getName2 = Employee::getName; 

    // B. (Employee e, String s) -> e.setName(s)
    BiConsumer<Employee, String> setName1 = (Employee e, String s) -> e.setName(s);
    BiConsumer<Employee, String> setName2 = Employee::setName; 

    // C. (String s1, String s2) -> s1.compareTo(s2)
    BiFunction<String, String, Integer> compareTo1 = (String s1, String s2) -> s1.compareTo(s2);
    BiFunction<String, String, Integer> compareTo2 = String::compareTo; 

    // D. (Integer x, Integer y) -> Math.pow(x, y)
    BiFunction<Integer, Integer, Double> pow1 = (Integer x, Integer y) -> Math.pow(x, y);
    BiFunction<Integer, Integer, Double> pow2 = Math::pow; // Method reference type: Class::staticMethod

    // E. (Apple a) -> a.getWeight()
    Function<Apple, Double> getWeight1 = (Apple a) -> a.getWeight();
    Function<Apple, Double> getWeight2 = Apple::getWeight; 

    // F. (String x) -> Integer.parseInt(x);
    Function<String, Integer> parseInt1 = (String x) -> Integer.parseInt(x);
    Function<String, Integer> parseInt2 = Integer::parseInt; // Method reference type: Class::staticMethod

    // G. EmployeeNameComparator comp = new EmployeeNameComparator();
    // (Employee e1, Employee e2) -> comp.compare(e1, e2)
    EmployeeNameComparator comp = new EmployeeNameComparator();
    BiFunction<Employee, Employee, Integer> compareEmployees1 = (Employee e1, Employee e2) -> comp.compare(e1, e2);
    BiFunction<Employee, Employee, Integer> compareEmployees2 = comp::compare; // Method reference type: Instance::instanceMethod

    // Evaluator method
    void evaluator() {
        // Test the lambda expressions and method references
        Employee e1 = new Employee("Alice");
        Employee e2 = new Employee("Bob");
        Apple apple = new Apple(1.5);

        // A
        System.out.println(getName2.apply(e1)); // Output: Alice

        // B
        setName2.accept(e1, "Charlie");
        System.out.println(getName2.apply(e1)); // Output: Charlie

        // C
        System.out.println(compareTo2.apply("hello", "world"));

        // D
        System.out.println(pow2.apply(2, 3)); // Output: 8.0

        // E
        System.out.println(getWeight2.apply(apple)); // Output: 1.5

        // F
        System.out.println(parseInt2.apply("123")); // Output: 123

        // G
        System.out.println(compareEmployees2.apply(e1, e2)); 
    }

    public static void main(String[] args) {
        Examples examples = new Examples();
        examples.evaluator();
    }
}
