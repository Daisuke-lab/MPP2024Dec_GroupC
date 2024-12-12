package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Consumer;


public class ForEachExample {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		List<String> list = Arrays.asList("Hello there", "Goodbye", "Back soon", 
				"Away", "On Vacation", "Everywhere you want to be");
		
		//print each element of the list in upper case format
		list.forEach(consumer);

		// PLAY AROUND
		list.forEach(new Consumer<String>() {
			@Override
			public void accept(String s) {
				System.out.println(toUpper(s));
			}
		});

		for (int i=0; i < list.size(); i++) {
			System.out.println(toUpper(list.get(i)));
		}
		for (String s : list) {
			System.out.println(toUpper(s));
		}

		list.forEach(s -> System.out.println(toUpper(s)));

		// This doesn't work.
		list.forEach(((Consumer<String>)ForEachExample::toUpper).andThen(System.out::println));
		
		
	}
	
	public static String toUpper(String s) {
		return s.toUpperCase();
	}
	
	//implement a Consumer
    static Consumer<String> consumer = new Consumer<String>() {
		@Override
		public void accept(String s) {
			System.out.println(toUpper(s));
		}
	};
	
	
}