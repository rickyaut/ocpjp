package ocpjp;

import static java.lang.System.out;
import static java.lang.System.err;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.LongUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

@SuppressWarnings("unused")
public class FreeTest {
	/**
	 * 
	 */
	@Test
	public void question(){
		err.println("QUESTION XX");
	}

	@Test
	public void question5(){
		err.println("QUESTION 5");
		Map<Integer, String> map = new TreeMap<>();// TreeMap allows null in value
		map.put(1, "One");
		map.put(2, "Two");
		map.put(3, "Three");
		map.replace(1, "1", null);// replace with happen only when both key and value match
		map.replace(3, null);
		out.println(map.values());
	}
	
	/**
	 * Have a look at IntStream methods
	 */
	@Test
	public void question6(){
		err.println("QUESTION 6");
		ArrayList<Integer> ints = new ArrayList<>();
		//IntStream.range(0, 9).forEach(ints::add);// [0, 1, 2, 3, 4, 5, 6, 7, 8]
		IntStream.rangeClosed(0, 10).forEach(ints::add);// [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
	}
	
	/**
	 * Read this: http://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/
	 * Thread->Runnable
	 * Executor.submit(Runnable)
	 * Executor.submit(Callable)
	 * Executor.invokeAll(Callable)
	 * Executor.invokeAny(Callable)
	 * ScheduledExecutorService.schedule
	 * ScheduledExecutorService.scheduleAtFixedRate
	 * ScheduledExecutorService.scheduleWithFixedDelay
	 */
	@Test
	public void question9(){
		err.println("QUESTION 9");
		Runnable r = ()->out.println(Thread.currentThread().getName());
		
		new Thread(r).start();
		
		ExecutorService executor = Executors.newFixedThreadPool(5);
		Future<Boolean> f1 = executor.submit(()-> true);
		try {
	        out.println(f1.get());
        } catch (InterruptedException | ExecutionException e) {
	        e.printStackTrace();
        }
		Future f2 = executor.submit(()->out.println(Thread.currentThread().getName()));
		try {
	        out.println(f2.get());
        } catch (InterruptedException | ExecutionException e) {
	        e.printStackTrace();
        }
		
		executor.execute(()->out.println(Thread.currentThread().getName()));//execute does not return anything
		try {
	        List<Future<Object>> results = executor.invokeAll(Arrays.asList(/*Callable objects here*/));
        } catch (InterruptedException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		try {
	        List<Future<Object>> results = executor.invokeAny(Arrays.asList(/*Callable objects here*/));
        } catch (InterruptedException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        } catch (ExecutionException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(3);
		//scheduledExecutor.schedule(command, delay, unit);
		//scheduledExecutor.schedule(callable, delay, unit)
		//scheduledExecutor.scheduleAtFixedRate(command, initialDelay, period, unit);
		//scheduledExecutor.scheduleWithFixedDelay(command, initialDelay, delay, unit);
	}
	
	/**
	 * 
	 */
	@Test
	public void question11(){
		err.println("QUESTION 11");
		Path path = Paths.get("pom.xml");
		try {
			out.println(path);
	        out.println(Files.readAttributes(path, "*"));
        } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		path = Paths.get("src", "main", "java");
		try {
			out.println(path);
	        out.println(Files.readAttributes(path, "*"));
        } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
	}
	/**
	 * 
	 */
	@Test
	public void question13(){
		err.println("QUESTION 13");
		Comparator<Integer> comparator = (i1, i2) -> Integer.compare(i1, i2);
	}

	/**
	 * 
	 */
	@Test
	public void question14(){
		err.println("QUESTION 14");
		Stream<String> stream = Stream.of("Dog", "Cat", "Mouse");
		Stream<Integer> list = stream.flatMap(s->Stream.of(s.length()));
		out.println(list.collect(Collectors.toSet()));
		out.println(list.collect(Collectors.toList()));
	}
	
	/**
	 * 
	 */
	@Test
	public void question15(){
		err.println("QUESTION 15");
		LongUnaryOperator lou = l->l*2;
		long l = lou.compose(lou).applyAsLong(3);
		out.println(l);
	}

	/**
	 * 
	 */
	@Test
	public void question17(){
		err.println("QUESTION 17");
		out.println(DateFormat.getDateInstance(DateFormat.LONG, new Locale("en")).format(new Date()));
		out.println(DateFormat.getDateInstance(DateFormat.SHORT, new Locale("en")).format(new Date()));
				
	}

}
