package ocpjp;

import static java.lang.System.err;
import static java.lang.System.out;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class PracticeTestI {
	
	/**
	 * Path.resolve
	 * Path.relative
	 */
	@Test
	public void question37_38(){
		err.println("QUESTION 37, 38");
		Path path1 = Paths.get("C:\\Users\\ricky.liu\\Documents\\workspace");
		out.println(path1.getNameCount());//4
		out.println(path1.getName(2));//Documents
		out.println(path1.getRoot());//C:\
		out.println(path1.getFileName());//workspace
		Path path2 = Paths.get("ocpjp", "numbers.txt");
		out.println(path2.getRoot());//relative path does not have root, so returned null
		out.println(path2.getNameCount());//2
		out.println(path2.getName(1));//numbers.txt
		out.println(path2.toAbsolutePath());//C:\Users\ricky.liu\workspace\ocpjp\ocpjp\numbers.txt
		out.println(path1.resolve(path2));//C:\Users\ricky.liu\Documents\workspace\ocpjp\numbers.txt
		
		//returns path1 directly because path1 is absolute path
		out.println(path2.resolve(path1));//C:\Users\ricky.liu\Documents\workspace
		
		try{
			out.println(path1.relativize(path2));
			out.println(path2.relativize(path1));
		}catch(IllegalArgumentException ex){
			out.println("They both throw IllegalArgumentException: other is different type of Path");
		}
		try {
			List<String> lineList = Files.readAllLines(path2);
			byte[] bytes = Files.readAllBytes(path2);
			Stream<String> lineStream = Files.lines(path2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Path path3 = Paths.get(path1.toString(), "ocpjp", "numbers.txt");
		out.println(path1.relativize(path3));//ocpjp\numbers.txt
		out.println(path3.relativize(path1));// ..\..
		try{
			out.println(Paths.get("C:\\Programs").relativize(Paths.get("D:\\Hello")));
		}catch(IllegalArgumentException ex){
			out.print("Exception: other has different root");
		}
	}

	/**
	 * 
	 */
	@Test
	public void question15(){
		err.println("QUESTION 15");
		class A implements AutoCloseable{

			@Override
            public void close() { //it is defined to throw Exception in AutoCloseable
            }
			
		}
		try(A a = new A()){}// since A.close() does not throw Exception, we can omit catch/finally block, but we have to keep the curly brackets
		
	}

	/**
	 * 
	 */
	@Test
	public void question20(){
		err.println("QUESTION 20");
		List<Integer> numbers = Arrays.asList(10, 11, 13, 19, 5);
		numbers.stream().filter(p -> p > 10).forEach(out::println);
		numbers.parallelStream().sequential().parallel();//convert between sequential stream and parallel stream
	}

	/**
	 * 
	 */
	@Test
	public void question26(){
		err.println("QUESTION 26");
		try{
			//Thread.sleep is static method
			//it does not throw exception if it is waked because of timeout
			Thread.currentThread().sleep(1000);
		}catch(InterruptedException ex){
			out.println(ex.getMessage());
		}
	}

	/**
	 * 
	 */
	@Test
	public void question35(){
		err.println("QUESTION 35");
		Path path = Paths.get("abc");
		try {
	        Files.delete(path);
        } catch (NoSuchFileException e) {//pay attention to this UNCHECKED exception
        } catch (DirectoryNotEmptyException e) {//pay attention to this UNCHECKED exception
        } catch (IOException e) {
	        e.printStackTrace();
        }
	}
	public void question49(){
		err.println("QUESTION 49");
		Stream<String> stream = Stream.of("1", "2", "3", "4", "5");
		
		//both averagingInt and averagingDouble return Double
		Double d1 = stream.collect(Collectors.averagingInt(i -> Integer.parseInt(i)));
		Double d2 = stream.collect(Collectors.averagingDouble(i -> Double.parseDouble(i)));
	}

	/**
	 * 
	 */
	@Test
	public void question52(){
		err.println("QUESTION 52");
		BiFunction<String, String, Integer> biFunction = ( a, b ) -> a.compareTo(b);
		Collections.sort(Arrays.asList(""), biFunction::apply);// usage of apply
		Collections.sort(Arrays.asList(""), ( a, b ) -> a.compareTo(b));// usage of apply
		
	}

	/**
	 * 
	 */
	@Test
	public void question(){
		err.println("QUESTION XX");
	}

}
