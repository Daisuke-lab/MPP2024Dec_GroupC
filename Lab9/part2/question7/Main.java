import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
public class Main {

	public static void main(String[] args) {
		List<Integer> intList = Arrays.asList(4, 5, -2, 0, -3, -1, -5, -4);
		//expected output: [0, -1, -2, -3, -4, 4, -5, 5]
		
		 
		
		ordering1(intList);
		List<String> stringList = Arrays.asList("cba", "efg", "doe", "fie", "set");
		//expected output: [cba, fie, doe, efg, set]
		ordering2(stringList);
		
	}
	
	//Orders the integers according to this pattern:
	// 0, -1, 1, -2, 2, -3, 3, . . .
	//Using this ordering, this method sorts the list as part of 
	//a stream pipeline, and prints to the console
	public static void ordering1(List<Integer> list) {
		System.out.println(list.stream().sorted(
            Comparator.comparing((Integer a)->Math.abs(a)).thenComparing((Integer a)->a)).collect(Collectors.toList()));
        
		
	}
	

	public static void ordering2(List<String> words) {
		System.out.println(words.stream()
			
				.sorted(Comparator.comparing(
						x -> (new StringBuilder(x)).reverse().toString()))
				.collect(Collectors.toList()));
				
	}

}
