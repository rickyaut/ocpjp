package ocpjp;

import static java.lang.System.err;
import static java.lang.System.out;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.ToDoubleBiFunction;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

public class PracticeTestIII {
	
	
	/**
	 * 
	 */
	@Test
	public void question5_6_7(){
		err.println("QUESTION 5, 6, 7");
		//There are three kinds of ResultSet types: 
		//TYPE_FORWARD_ONLY: cursor can only move forward
		//TYPE_SCROLL_INSENSITIVE: cursor can scroll and resultset is not sensitive to changes
		//		made by others to the database that occur afterr the result set was created.
		//TYPE_SCROLL_SENSITIVE: cursor can scroll and resultset is sensitive to changes.
		
		//ResultSet.absolute(3) move cursor to the third row
		//ResultSet.getInt(4) get int field value from fourth column
		//ResultSet.getString("First_Name") get string value from "First_Name" field
		//ResultSet.updateInt(4, 100)
		//ResultSet.updateString("First_Name", "Ricky")
		//ResultSet.updateRow() update existing row
		
		//ResultSet.moveToInsertRow() start the insert operation
		//ResultSet.updateString("First_Name", "Ricky")
		//ResultSet.insertRow()
		//ResultSet.moveToCurrentRow()
		
		//Statement.execute() and Statement.executeUpdate() allows using "update" SQL statement
	}

	/**
	 * 
	 */
	@Test
	public void question22_23_24(){
		err.println("QUESTION 22, 23, 24");
		TreeMap<String, String> tmap = new TreeMap<>();
		tmap.put("AA", "a");
		tmap.put("BB", "b");
		tmap.put("CC", "c");
		tmap.put("DD", "d");
		tmap.put("EE", "e");
		
		out.println(tmap.subMap("BB", "BB"));//empty
		out.println(tmap.subMap("BB", true, "BB", true));//{BB=b}
		try{
			out.println(tmap.subMap("DD", "BB"));//fromKey > toKey
		}catch(IllegalArgumentException ex){}
		
		//subMap is method of TreeMap class, not the method of Map interface, or HashMap class
		SortedMap<String, String> subMap = tmap.subMap("BB", "DD");//BB=b
		out.println(subMap);//{BB=b, CC=c}
		subMap.put("BC", "bc");
		out.println(subMap);
		out.println(tmap);// BC=bc is also added to both Maps
		subMap.remove("BC");
		out.println(subMap);
		out.println(tmap);// BC=bc is now removed from both Mapss
		try{
			subMap.put("XX", "xx");//key out of range
		}catch(IllegalArgumentException ex){}
		out.println("ABC".substring(1, 1));//empty
		
		Map<PracticeTestIII, String> map2 = new TreeMap<>();//This is OK
		try{
			map2.put(new PracticeTestIII(), "something");//Key has to be Comparable
		}catch(ClassCastException ex){}
		
		List<String> list = Arrays.asList("AA", "BB", "CC", "DD", "EE");//this is fixed size list, cannot add new elements
		try{
			list.add("XX");
		}catch(UnsupportedOperationException ex){}
		list = new ArrayList<String>(list);
		list.add("FF");
		List<String> subList = list.subList(2, 3);
		out.println(subList);
		subList.add(0, "XX");
		out.println(subList);
		out.println(list);
		try{
			subList.add(10, "YY");
		}catch(IndexOutOfBoundsException ex){}
		
		ArrayDeque<Integer> dq = new ArrayDeque<>();
		dq.add(10);
		dq.add(20);
		dq.add(30);
		dq.add(40);
		//add, addFirst, addLast
		//offer, offerFirst, offerLast
		//push, pop
		//peek, peekFirst, peekLast 
		//element, getFirst, getLast throw NoSuchElementException(run time exception)
		//poll, pollFirst, pollLast
		//remove, removeFist, removeLast return the removed object, otherwise throw NoSuchElementException
		//remove(Object o), removeFirstOccurrence, , removeLastOccurrence return true/false
		out.println(String.format("element: %d, poll: %d, element: %d, poll: %d", dq.element(), dq.poll(), dq.element(), dq.poll()));
	}

	/**
	 * 
	 */
	class Member{
		private String name;
		private int age;
		public Member(String name, int age) {
	        super();
	        this.name = name;
	        this.age = age;
        }
		@Override
        public int hashCode() {
	        return name.length();
        }
		@Override
        public boolean equals(Object obj) {
	        return age == ((Member)obj).age;
        }
		@Override
        public String toString() {
	        return "Member [name=" + name + "]";
        }
	}
	@Test
	public void question32(){
		err.println("QUESTION 32");
		Set<Member> set = new HashSet<Member>();
		set.add(new Member("Ricky", 30));
		set.add(new Member("John", 30));
		set.add(new Member("Sam", 30));
		set.add(new Member("Cliff", 30));//the EQUAL element with same hashCode will be replaced 
		out.println(set);// [Member [name=Sam], Member [name=John], Member [name=Ricky]]
		set.add(new Member("Ricky", 32));
		out.println(set);//[Member [name=Sam], Member [name=John], Member [name=Ricky], Member [name=Ricky]]
		out.println(set.remove(new Member("Cliff", 30)));//if there is an EQUAL element
		out.println(set);// [Member [name=Sam], Member [name=John], Member [name=Ricky]]
	}
	@Test
	public void question38(){
		err.println("QUESTION 38");
		int ints[] = {4, 6, 8, 10};
		Arrays.parallelPrefix(ints, Integer::sum);
		out.println(Arrays.toString(ints)); //[4, 10, 18, 28]
		ints = new int[]{4, 6, 8, 10};
		Arrays.parallelPrefix(ints, (a, b) -> a + b);
		out.println(Arrays.toString(ints));//[4, 10, 18, 28]
		
		out.println(Arrays.binarySearch(new int[]{4, 6, 8, 10}, 6));//1
		out.println(Arrays.binarySearch(new int[]{4, 6, 8, 10}, 1/*inclusive*/, 3/*exclusive*/, 6));//1
		out.println(Arrays.toString(Arrays.copyOf(new int[]{4, 6, 8, 10}, 10)));//[4, 6, 8, 10, 0, 0, 0, 0, 0, 0]
		out.println(Arrays.toString(Arrays.copyOf(new int[]{4, 6, 8, 10}, 2)));//[4, 6]
		out.println(Arrays.toString(Arrays.copyOfRange(new int[]{4, 6, 8, 10}, 2, 6)));//[8, 10, 0, 0]
		out.println(Arrays.toString(Arrays.copyOfRange(new int[]{4, 6, 8, 10}, 2, 3/*exclusive*/)));//[8]
		
	}

	/**
	 * 
	 */
	@Test
	public void question52(){
		err.println("QUESTION 52");
		List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5);
		Optional<Integer> optional1 = ints.stream().findAny();
		Optional<Integer> optional2 = ints.stream().findFirst();
		Optional<Integer> optional3 = ints.stream().max(Integer::compare);
		Optional<Integer> optional4 = ints.stream().min(Integer::compare);
		IntStream intStream1 = ints.stream().mapToInt(Integer::intValue);
		Stream<Integer> stream = intStream1.boxed();
		
		//IntOptional does not have filter or map method
		Optional<Integer> optional5 = optional1.filter(i -> i>1);
		Optional<Integer> optional6 = optional1.map(i -> i+1);
		optional5.ifPresent(out::println);
		
		IntStream intStream2 = IntStream.of(1, 2, 3, 4, 5);
		OptionalDouble optional11 = IntStream.of(1, 2, 3, 4, 5).average();
		OptionalInt optional12 = IntStream.of(1, 2, 3, 4, 5).min();
		OptionalInt optional13 = IntStream.of(1, 2, 3, 4, 5).max();
		optional13.ifPresent(out::println);
		
		int sum = IntStream.of(1, 2, 3, 4, 5).sum();
		long count = IntStream.of(1, 2, 3, 4, 5).count();//not int
		
		
		Predicate<Double> pre1 = d -> d>1;
		DoublePredicate pre2 = d -> d>1;
		Predicate<Integer> pre3 = d -> d>1;
		IntPredicate pre4 = d -> d > 1;
		
		// compile error
		//IntStream.of(1, 2, 3, 4, 5).anyMatch(pre1);
		// compile error
		//IntStream.of(1, 2, 3, 4, 5).anyMatch(pre2);
		// compile error
		//IntStream.of(1, 2, 3, 4, 5).anyMatch(pre3);
		IntStream.of(1, 2, 3, 4, 5).anyMatch(pre4);
		
		// compile error
		//Stream.of(1, 2, 3, 4, 5).anyMatch(pre1);
		// compile error
		//Stream.of(1, 2, 3, 4, 5).anyMatch(pre2);
		Stream.of(1, 2, 3, 4, 5).anyMatch(pre3);
		// compile error
		//Stream.of(1, 2, 3, 4, 5).anyMatch(pre4);

		// compile error
		//DoubleStream.of(1, 2, 3, 4, 5).anyMatch(pre1);
		DoubleStream.of(1, 2, 3, 4, 5).anyMatch(pre2);
		// compile error
		//DoubleStream.of(1, 2, 3, 4, 5).anyMatch(pre3);
		// compile error
		//DoubleStream.of(1, 2, 3, 4, 5).anyMatch(pre4);
		
		Stream.of(1.0, 2.0, 3.0, 4.0, 5.0).anyMatch(pre1);
		// compile error
		//Stream.of(1.0, 2.0, 3.0, 4.0, 5.0).anyMatch(pre2);
		// compile error
		//Stream.of(1.0, 2.0, 3.0, 4.0, 5.0).anyMatch(pre3);
		// compile error
		//Stream.of(1.0, 2.0, 3.0, 4.0, 5.0).anyMatch(pre4);
		
		
	}

	/**
	 * 
	 */
	@Test
	public void question61_62_64(){
		err.println("QUESTION 61, 62, 64");
		Stream<Double> stream2 = Stream.of(11.1, 12.2, 13.3);
		Stream<Integer> list2 = stream2.flatMap(d -> Stream.of(d.intValue()));
		
		Map<Integer, Double> map = new HashMap<>();
		ToDoubleBiFunction<Integer, Double> tdf = (a, b) -> a + b;
		map.forEach((k, v) -> out.println(tdf.applyAsDouble(k, v)));
	}

	/**
	 * 
	 */
	@Test
	public void question(){
		err.println("QUESTION XX");
	}

}
