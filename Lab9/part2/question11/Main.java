
import java.util.*;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		List<Employee> emps = Arrays.asList(new Employee("Joe", "Davis", 120000),
				new Employee("John", "Sims", 110000),
				new Employee("Joe", "Stevens", 200000),
				new Employee("Andrew", "Reardon", 80000),
				new Employee("Joe", "Cummings", 760000),
				new Employee("Steven", "Walters", 135000),
				new Employee("Thomas", "Blake", 111000),
				new Employee("Alice", "Richards", 101000),
				new Employee("Donald", "Trump", 100000));

		//-----part A-----//
		// Stream pipeline to filter, map, sort, and collect
		String result = emps.stream()
				.filter(e -> e.getSalary() > 100000) 
				.filter(e -> e.getLastName().charAt(0) >= 'N') 
				.map(Employee::fullName) 
				.sorted() 
				.collect(Collectors.joining(", ")); 

		System.out.println(result);
	
		//------part B---//
		// Use the LambdaLibrary
        String result2 = LambdaLibrary.EMPLOYEES_WITH_HIGH_SALARY_AND_NAME_RANGE
                .apply(emps, 100000, 'N');

        System.out.println(result2);
	}

}
