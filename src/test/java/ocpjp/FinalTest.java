package ocpjp;

import static java.lang.System.err;
import static java.lang.System.out;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

public class FinalTest {
	/**
	 * 
	 */
	@Test
	public void question1(){
		err.println("QUESTION 1");
		Comparable<Integer> com1 = new Comparable<Integer>() {

			@Override
			public int compareTo(Integer o) {
				return -1;
			}
		};
		//Compile error: <> cannot be used on anonymous class
//		Comparable<Integer> com2 = new Comparable<>() {
//
//			@Override
//			public int compareTo(Integer o) {
//				return -1;
//			}
//		};
		//Compile error: must implement compoareTo(Object o), not compoareTo(Integer o)
//		Comparable<Integer> com3 = new Comparable() {
//
//			public int compareTo(Integer o) {
//				return -1;
//			}
//		};
		
		//this one is same as com1
		Comparable com4 = o -> -1;
		Comparable<Integer> com5 = o -> Integer.compare(o, 0);
		out.println(com5.compareTo(4));
		Comparator<String> com6 = Comparator.comparing(s -> s.toLowerCase());
		Stream.of("ABC", "DEF").sorted(com6).min(com6);
		Stream.of("ABC", "DEF").findAny().ifPresent(s -> out.println(s));
		out.println(Stream.of("ABC", "DEF").findFirst().orElse("XYZ"));
		//min, max of Object Stream requires Comparator as parameter
		//the result is Optional<...>
		Stream.of("ABC", "DEF").min((x, y) -> x.compareTo(y));

		//min, max of IntStream does not require parameter
		//the result is OptionalInt
		IntStream.of(2, 5).min();
	}


	/**
	 * @throws IOException 
	 * 
	 */
	@Test
	public void question42() throws IOException{
		err.println("QUESTION 42");
		Files.find(Paths.get("src"), 0, (p, b) -> true).forEach(path -> out.println(path));//src
		Files.find(Paths.get("src"), 1, (p, b) -> true).forEach(path -> out.println(path));//src, src\main, src\test
		Files.walk(Paths.get("src"), 0).forEach(path -> out.println(path));;
	}

	/**
	 * 
	 */
	@Test
	public void question48(){
		err.println("QUESTION 48");
		try {
			byte bytes1[] = new byte[10];
			byte bytes2[] = new byte[10];
			byte bytes3[] = new byte[10];
			// input:  0123456789123
			System.in.read(bytes1);//[48, 49, 50, 51, 52, 53, 54, 55, 56, 57]
			System.in.read(bytes2, 2, 4);//[0, 0, 49, 50, 51, 13, 0, 0, 0, 0] character CR is included
			System.in.read(bytes3);//[10, 0, 0, 0, 0, 0, 0, 0, 0, 0]
			
			byte bytes4[] = new byte[10];
			byte bytes5[] = new byte[10];
			byte bytes6[] = new byte[10];
			// input:  0123456789123456
			System.in.read(bytes4, 2, 4);//[0, 0, 48, 49, 50, 51, 0, 0, 0, 0]
			System.in.read(bytes5, 2, 4);//[0, 0, 52, 53, 54, 55, 0, 0, 0, 0]
			System.in.read(bytes6);//[56, 57, 49, 50, 51, 52, 53, 54, 13, 10]
			out.println();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * 
	 */
	@Test
	public void question55(){
		err.println("QUESTION 55");
		Optional<String> op = Optional.of("10");
		Optional<Integer> op1 = op.map( s -> Integer.parseInt(s));
		Optional<Integer> op2 = op.flatMap( s -> Optional.of(Integer.parseInt(s)));
	}

	/**
	 * 
	 */
	@Test
	public void question79(){
		err.println("QUESTION 79");
		Instant now = Instant.now();
		out.println(now);//2017-06-16T03:54:23.598Z
		//only these fields are supported:
		// NANO_OF_SECOND
		out.println(now.getLong(ChronoField.NANO_OF_SECOND));//598000000
		out.println(now.get(ChronoField.NANO_OF_SECOND));//598000000
		// MICRO_OF_SECOND
		out.println(now.getLong(ChronoField.MICRO_OF_SECOND));//598000
		out.println(now.get(ChronoField.MICRO_OF_SECOND));//598000
		// MILLI_OF_SECOND
		out.println(now.getLong(ChronoField.MILLI_OF_SECOND));//598
		out.println(now.get(ChronoField.MILLI_OF_SECOND));//598
		// INSTANT_SECONDS
		out.println(now.getLong(ChronoField.INSTANT_SECONDS));//1497585263
		//throw DateTimeException: Invalid value for InstantSeconds
		out.println(now.get(ChronoField.INSTANT_SECONDS));
		//throw UnsupportedTemporalTypeException
		out.println(now.get(ChronoField.HOUR_OF_DAY));
		out.println(now.getLong(ChronoField.HOUR_OF_DAY));
		
		//Period.toString() -- PxYxMxD
		//Duration.toString() -- PTxHxMx.xS
	}

	/**
	 * 
	 */
	@Test
	public void question(){
		err.println("QUESTION XX");
		Map<String, String> map = new HashMap<>();
		map.put("A", "a");
		map.put(null, "b");
		out.println(map);//{null=b, A=a}
	}

}
