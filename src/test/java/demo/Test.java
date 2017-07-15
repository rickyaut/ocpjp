package demo;

import static java.lang.System.out;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test {

	public static void main(String[] args) {
		method1();
	}

	private static void method1() {
	    List<String> strings = Arrays.asList("Ricky", "Nico", "Andrew");
	    
		Comparator<String> comp = (a, b) -> a.length() - b.length();
		
		Collections.sort(strings, comp);
    }

	private static void method2() {
		
	    List<String> strings = Arrays.asList("Ricky", "Nico", "Andrew");
	    
		Collections.sort(strings, (a, b) -> a.length() - b.length());
    }
	
	private static void method3(){
		
		IC v = d -> d.length() > 5;
		
		if(v.isValid("Ricky")){
			System.out.println("Name is valid");
		}else{
			System.out.println("Name is invalid");
		}
		
	}
	
	private static void method4(){
		check("Ricky", d -> d.length() > 5);
	}
	
	private static void check(String string, IC v) {
		System.out.println(v.isValid("Ricky") ? "valid" : "Invalid");
    }
	
	private static void method5() throws IOException{
		Stream<String> stream1 = Stream.of("a", "b", "c", "d");
		
		Stream<String> stream2 = Arrays.asList("a", "b", "c", "d").stream();
		
		Stream<String> stream3 = Files.lines(Paths.get("Test.java"));
		
		IntStream stream4 = IntStream.of(1, 3, 5);
		
		Stream<String> stream5 = stream1.parallel();
		
		Stream<String> stream6 = stream1.map(e -> e + e);
		
		Stream<String> stream7 = stream1.filter( e -> e.compareTo("a")>0);

		Stream<String> stream8 = stream1.skip(1).limit(2);
		
		Stream<String> stream9 = stream1.distinct();
		
		Stream<String> stream10 = stream1.sorted();

		boolean b1 = stream1.anyMatch(e -> e.compareTo("a")>0);
		boolean b2 = stream1.allMatch(e -> e.compareTo("a")>0);
		boolean b3 = stream1.noneMatch(e -> e.compareTo("a")>0);
		
		stream1.forEach(e->out.println(e));
		
		Optional<String> s1 = stream1.max((e1, e2) -> e1.compareTo(e2));
		
		OptionalDouble d1 = stream4.average();
		
		IntStream streama = IntStream.of(1, 3, 5);
		
		Stream<Integer> streamb = streama.boxed();
		
		IntStream  steamc = streamb.mapToInt(e -> e);
		
		
	}
	
	void method6(){
		class MyResource implements AutoCloseable{
			void getData(){}

			@Override
			public void close() throws Exception {
				out.println("closing");
			}
			
		}
		
		try(MyResource resource = new MyResource()){
			resource.getData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void method7(){
		class MyResource implements AutoCloseable{
			void getData(){}

			@Override
			public void close() {
				out.println("closing");
			}
			
		}
		
		try(MyResource resource = new MyResource()){
			resource.getData();
		}
	}
}
