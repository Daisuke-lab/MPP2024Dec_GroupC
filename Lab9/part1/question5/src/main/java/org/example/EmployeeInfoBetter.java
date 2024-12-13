package org.example;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.Map;
import java.util.HashMap;

public class EmployeeInfoBetter {
	static enum SortMethod {BYNAME, BYSALARY};
	static Function<Employee, String> byName = e -> e.getName();
	static Function<Employee, Integer> bySalary = e -> -e.getSalary();
	static Map<SortMethod, Pair<Function, Function>> sortMap = new HashMap<>()
	{{
		put(SortMethod.BYNAME, new Pair<>(byName, bySalary));
		put(SortMethod.BYSALARY, new Pair<>(bySalary, byName));
	}};

	
	public void sort(List<Employee> emps, final SortMethod method) {
		Collections.sort(emps, Comparator.comparing(sortMap.get(method).getKey()).thenComparing(sortMap.get(method).getValue()));
	}
	
	
	
	public static void main(String[] args) {
		List<Employee> emps = new ArrayList<>();
		emps.add(new Employee("Joe", 100000));
		emps.add(new Employee("Tim", 50000));
		emps.add(new Employee("Rick", 50000));
		emps.add(new Employee("Andy", 60000));
		emps.add(new Employee("Tim", 10000));
		EmployeeInfoBetter ei = new EmployeeInfoBetter();
		ei.sort(emps, SortMethod.BYNAME);
		System.out.println(emps);
		//same instance
		ei.sort(emps, SortMethod.BYSALARY);
		System.out.println(emps);
	}
}
