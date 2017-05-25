package ocpjp;

import static java.lang.System.err;
import static java.lang.System.out;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Year;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.DoubleSupplier;
import java.util.function.ToDoubleBiFunction;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

public class DiagnosticTest {
	
	/**
	 * 
	 */
	static int i;
	public class ClassDefinedInClass{
		public void print(){
			out.println(i);
		}
	}
	public static class StaticClassDefinedInClass{
		public void print(){
			out.println(i);
		}
		public static void staticPrint(){//static method only in static inner class
			out.println(i);
		}
	}
	@Test
	public void question1(){
		err.println("QUESTION 1");
		abstract class ClassDefinedInMethod1{} // A class defined in method can only be final or abstract, it cannot be static, public or protected
		final class ClassDefinedInMethod2{} // A class defined in method can only be final or abstract, it cannot be static, public or protected
		ClassDefinedInClass obj1 = this.new ClassDefinedInClass();
		StaticClassDefinedInClass obj2 = new StaticClassDefinedInClass();
		ClassDefinedInMethod1 obj3 = new ClassDefinedInMethod1(){};
		ClassDefinedInMethod2 obj4 = new ClassDefinedInMethod2();
	}

	/**
	 * 
	 */
	interface FunctionalMethod<T> {
		T apply(T t);
	}
	@Test
	public void question5(){
		err.println("QUESTION 5");
		//compile error - FunctionalMethod<String> f1 = String::length;
		FunctionalMethod<String> f2 = String::toUpperCase;
		//compile error - FunctionalMethod<String> f3 = s->return s;
		FunctionalMethod<String> f4 = s->{return s;};
		//compile error - FunctionalMethod<String> f5 = ()->"ABC";
	}

	/**
	 * 
	 */
	enum Value{
		HIGH(1), MEDIUM(6), LOW(3);
		private final int level;// final field can be initialized in constructor
		private Value(int levelCode){
			level = levelCode;
		}
	}
	@Test
	public void question7(){
		err.println("QUESTION 7");
		Value values[] = {Value.LOW, Value.HIGH, Value.MEDIUM};
		Arrays.sort(values);
		out.println(values[1]);
	}

	/**
	 * 
	 */
	@Test
	public void question13(){
		err.println("QUESTION 13");
		assert false; //When assertion is enabled, it should throw AssertionError
		assert false:"something wrong"; //When assertion is enabled, it should throw AssertionError with detailed message
		out.println("Good");
	}

	/**
	 * https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
	 * AutoCloseable and try-with-resources
	 */
	@Test
	public void question14(){
		err.println("QUESTION 14");
		AutoCloseable closable = new AutoCloseable() {
			
			@Override
			public void close() throws Exception {
				// TODO Auto-generated method stub
				
			}
		};
	}
	
	/**
	 * How to create Stream object
	 */
	@Test
	public void question17(){
		err.println("QUESTION 17");
		Stream<Integer> intStream1 = Stream.of(1, 2, 4);// from array
		Stream<Integer> intStream2 = Arrays.asList(1, 2, 3).stream(); // from list
		//from file
		try(Stream<String> stringStream = Files.lines(Paths.get("numbers.txt"))){
			stringStream.mapToInt(s->Integer.parseInt(s)).forEach(out::print);
			
			//The folowing code throws java.lang.IllegalStateException: stream has been operated on or closed
			stringStream.mapToInt(s->{return Integer.parseInt(s);}).forEach(out::println);
		} catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
	}
	
	/**
	 * 
	 */
	@Test
	public void question19(){
		err.println("QUESTION 19");
		Map<String, Integer> map = new TreeMap<String, Integer>();
		//forEach(BiConsumer<? super K, ? super V> action) 
		map.forEach((k, v) ->out.println(k));
	}
	
	/**
	 * 
	 */
	@Test
	public void question21(){
		err.println("QUESTION 21");
		Stream<String> stream = Stream.of("A", "AB", "ABC", "ABCD", "AB");
		out.println(stream.filter(s->s.length() > 2).filter(s->s.indexOf("C")>-1).findAny().orElse("None"));
	}
	
	/**
	 * 
	 */
	@Test
	public void question22(){
		err.println("QUESTION 22");
		IntStream ds = IntStream.of(1, 2, 2, 4);
		Stream<String> ste = ds.boxed().map(t -> t.toString());
		
		//the statement below fail to compile because both toString() and toString(int) are eligible
		//Stream<String> ste2 = ds.boxed().map(Integer::toString);
	}
	/**
	 * https://www.leveluplunch.com/java/examples/stream-intermediate-operations-example/
	 * 
	 * Intermediate operations return another Stream which allows you to call multiple operations 
	 * in a form of a query. Intermediate operations do not get executed until a terminal operation 
	 * is invoked as there is a possibility they could be processed together when a terminal 
	 * operation is executed.
	 * 
	 * Stateful operation
	 * 
	 * Terminal operation
	 */
	@Test
	public void question24(){
		err.println("QUESTION 24");
	}
	
	/**
	 * usage of peek
	 * terminal operation
	 */
	@Test
	public void question25(){
		err.println("QUESTION 25");
		Stream<Integer> stream = Stream.of(4, 10, 8, 3);
		out.println(stream.peek(out::print).anyMatch(x->x>=10));
	}
	
	/**
	 * 
	 */
	@Test
	public void question28(){
		err.println("QUESTION 28");
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "A");
		map.put(2, "B");
		map.put(3, "C");
		map.put(4, "D");
		map.remove("A");
		map.remove(3, "C");
		map.remove(4, "B");
		map.values().forEach(out::print);
	}
	
	/**
	 * 
	 */
	@Test
	public void question29(){
		err.println("QUESTION 29");
		Comparator<int[]> comparator = Comparator.comparing(e->e.length);
	}
	
	/**
	 * usage of join
	 */
	class Ttest implements Runnable{

		@Override
        public void run() {
	        out.println(Thread.currentThread().getName());
        }
	}
	@Test
	public void question31(){
		err.println("QUESTION 31");
		Ttest a1 = new Ttest();
		Thread t = new Thread(a1);
		t.setName("a1");
		t.start();
        out.println(Thread.currentThread().getName());
        try{
        	t.join();
        }catch(Exception e){
        }
        out.println(Thread.currentThread().getName());
	}
	
	/**
	 * demonstrate How to use fork and join with ForkJoinPool
	 */
	static class Sum extends RecursiveTask<Long>{
		static final int MAX = 5000;
		int low, high, array[];
		
		Sum(int[] arr, int lo, int hi){
			array = arr;
			low = lo;
			high = hi;
		}
		
		@Override
        protected Long compute() {
	        if(high - low <= MAX){
	        	long sum = 0;
	        	for(int i=low; i < high; sum+=array[i++]);
	        	return sum;
	        }else{
	        	int mid = (low + high) >> 2;
	        	Sum left = new Sum(array, low, mid);
	        	Sum right = new Sum(array, mid, high);
	        	left.fork();
	        	long rightAns = right.compute();
	        	long leftAns = left.join();
	        	return leftAns + rightAns;
	        }
        }
		static long sumArray(int[] array){
			ForkJoinPool fPool = new ForkJoinPool();
			long sum = fPool.invoke(new Sum(array, 0, array.length));
			return sum;
		}
	}
	@Test
	public void question34(){
		err.println("QUESTION 34");
		int a[] = new int[15000];
		for(int x = 0; x < a.length; a[x++] = 1);
		out.println(Sum.sumArray(a));
	}
	
	/**
	 * @throws IOException 
	 * 
	 */
	@Test
	public void question35_36_37_38_39() throws IOException{
		err.println("QUESTION 35, 36, 37, 38, 39");
		out.println(Stream.of(3, 4, 5, 6, 7, 8, 9).parallel().collect(Collectors.groupingByConcurrent(i -> (Integer)i % 2 == 0 ? 1: 2)));
		//below statement results in compile error
		//out.println(Stream.of(3, 4, 5, 6, 7, 8, 9).parallel().collect(Collectors.groupingByConcurrent(i -> i % 2 == 0 ? 1: 2)));
		
		String[] list = {"1", "2", "3"};
		Arrays.parallelSetAll(list, x -> Integer.toString(x) + list[x]);
		out.println(list[0]);
		Stream<String> lineStream = Files.lines(Paths.get("pom.xml"));
		lineStream.skip(2).forEach(out::println);//skip first 2 lines
		List<String> lines = Files.readAllLines(Paths.get("pom.xml"));
		byte[] bytes = Files.readAllBytes(Paths.get("pom.xml"));
		
		Path path = Paths.get("F:\\Whizlabs\\java\\NIO\\abc\\123");
		out.println(path.getRoot());
		out.println(path.getFileSystem());
		out.println(path.getParent());
		
		//beginIndex inclusive, endIndex exclusive
		//which is save as substring, subList
		out.println(path.subpath(0, 3));
		
	}
	
	/**
	 * 
	 */
	@Test
	public void question43_49(){
		err.println("QUESTION 43, 49");
		Stream.of("A", "B").map(s-> s.substring(0, 1));
		
		//compilation error for flatMap
		//Stream.of("A", "B").flatMap(s-> s.substring(0, 1));
		
		//map function for flatMap has to return a Stream object
		Stream.of("AC", "BD").flatMap(s-> Stream.of(s.substring(0, 1))).forEach(out::println);;

		out.println(DoubleStream.of(10.0, 20.34, 43.10).average() );
		
	}
	
	/**
	 * 
	 */
	@Test
	public void question53(){
		err.println("QUESTION 53");
		out.println("a=" + Optional.of("1").isPresent());//a=true
		//out.println("b=" + Optional.of(null).isPresent());//NullPointerException
		out.println("c=" + Optional.ofNullable(null).isPresent());//c=false
		out.println("d=" + Optional.empty().isPresent());//d=false
		out.println("A=" + Optional.of("1").get());//A=1
		//out.println("C=" + Optional.ofNullable(null).get());//NoSuchElementException
		//out.println("D=" + Optional.empty().get());//NoSuchElementException
	}
	
	/**
	 * 
	 */
	@Test
	public void question63_68(){
		err.println("QUESTION 63, 68");
		Map<Integer, Double> map = new HashMap();
		map.put(1, 1.1);
		map.put(2, 2.2);
		map.put(1, 3.3);
		ToDoubleBiFunction<Double, Integer> tdf = (a, b) -> a+b;
		map.forEach((k, v) -> out.println(tdf.applyAsDouble(v, k)));// applyAsDouble INSTEAD of apply is the only method defined in ToDoubleBiFunction
		
		DoubleSupplier doubleSupplier = () -> Math.random()*10;
		out.println(doubleSupplier.getAsDouble()); //getAsDouble INSTEAD of "get" is the only method defined in DoubleSupplier
	}
	
	/**
	 * 
	 */
	@Test
	public void question70_71_78(){
		err.println("QUESTION 70, 71, 78");
		//How to use ResourceBundle
		LocalDate ld1 = LocalDate.of(2015, 11, 25);
		Year year = Year.of(2014);
		out.println(ld1.adjustInto(year.atDay(1)));
		out.println(year);
	}
	
	/**
	 * 
	 */
	@Test
	public void question(){
		err.println("QUESTION XX");
	}
	
}