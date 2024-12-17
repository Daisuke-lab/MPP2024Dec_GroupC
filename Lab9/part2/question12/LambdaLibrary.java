package lesson9.labs.prob12;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LambdaLibrary {
    //print the number of Employees in list whose salary > 100000 and whose last name begins
    //with a letter that comes after the letter 'E'
    public static final Function<List<Employee>, Long> NUM_EMPLOYEE_QUARY =
            (emps) ->
                    emps.stream()
                            .filter(emp -> emp.getSalary() > 100000)
                            .filter(emp -> emp.getLastName().charAt(0) > 'E')
                            .count();

    //print a list of sorted full names - all upper case -- of Employees with
    //salary > 85000 and whose first name begins with a letter that comes before  the letter 'R'
    public static final Function<List<Employee>, List<String>> SORTED_FULLNAME_QUARY =
            (emps) ->
                    emps.stream()
                            .filter(emp -> emp.getSalary() > 85000)
                            .filter(emp -> emp.getFirstName().charAt(0) > 'R')
                            .map(emp -> {
                                return (emp.getFirstName() + " " + emp.getLastName()).toUpperCase();
                            })
                            .sorted()
                            .collect(Collectors.toList());
}
