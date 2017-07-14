package ocpjp;

import static java.lang.System.err;
import static java.lang.System.out;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.ObjIntConsumer;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

public class PracticeTestI {
	
	/**
	 * 
	 */
	@Test
	public void question27(){
		err.println("QUESTION 27");
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
	                Thread.sleep(2000);
                } catch (InterruptedException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
                }
			}
		});
		t.start();
		out.println(new Date());
		//wait until thread ends try{t.join();}catch(InterruptedException ex){}
		//wait for 1 second only try{t.join(1000);}catch(InterruptedException ex){}
		//maximum wait for 4 seconds, next statement is executed immediately after 4 seconds OR thread ends
		try{t.join(4000);}catch(InterruptedException ex){}
		out.println(new Date());
	}

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
            public void close() { //it is defined in AutoCloseable to throw Exception
            }
			
		}
		class B implements AutoCloseable{

			@Override
            public void close() throws Exception{ //it is defined in AutoCloseable to throw Exception
            }
			
		}
		try(A a = new A()){}// since A.close() does not throw Exception, we can omit catch/finally block, but we have to keep the curly brackets
		
		try(B a = new B()){// since B.close() does throw Exception, we cannot omit catch/finally block
			
		} catch (Exception e) {
	        e.printStackTrace();
        }
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
			long timeout1 = 1500;
			Thread.currentThread().sleep(timeout1);
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
	public void question52_55(){
		err.println("QUESTION 52, 55");
		BiFunction<String, String, Integer> biFunction = ( a, b ) -> a.compareTo(b);
		Collections.sort(Arrays.asList(""), biFunction::apply);// usage of apply
		Collections.sort(Arrays.asList(""), ( a, b ) -> a.compareTo(b));// usage of apply
		
		IntStream ints = IntStream.of(3, 2, 1, 5, 3);
		//allMatch stop executing once it finds an element which doesnot match the given predicate
		ints.peek(out::print).allMatch(i -> i > 1);//321
	}

	/**
	 * 
	 */
	@Test
	public void question59(){
		err.println("QUESTION 59");
		Optional<String> op = Stream.of("one", "two", "three").filter(s -> s.length() > 5).findFirst().flatMap(s -> Optional.of("4"));
		out.println(op);//Optional.empty
		
		Optional<String[]> op2 = Optional.of(new String[]{"a", "b", "c"});
		op2.filter(s -> s.length >0).ifPresent(s-> out.println(s.length));
		
		//ifPresent returns void, so orElse will not work
		//op2.filter(s -> s.length >0).ifPresent(s-> out.println(s.length)).orElse(...);
	}

	/**
	 * 
	 */
	@Test
	public void question62_63_65(){
		err.println("QUESTION 62, 63, 65");
		ToDoubleFunction<String> func = s -> Double.parseDouble(s);
		Stream.of("100.0", "200.2", "45.4").mapToDouble(func).forEach(out::print);;
		
		//understand when "apply" should be used
		DoubleFunction<String> func2 = Double::toString;
		Stream.of(100.1, 200.2).map(func2::apply).forEach(out::print);;
		
		BiFunction<Integer, Double, String> biFunc = (i, d) -> (i + d) + "";
		Function<String, Double> function = Double::parseDouble;
		// compile error because BiFunction does not have compose method: biFunc.compose(function).apply(2, 1.0);
		// compile error because compose method of Function does not take BiFunction as parameter: function.compose(biFunc);
		
		//BinaryOperator, UnaryOperator
		//BinaryOperator is sub-interface of BiFunction
		//UnaryOperator is sub-interface of Function
		//compose method is defined in Function, the return type is always "Function", 
		//so the return type cannot be assigned to BiFunction, Operator, BinaryOperator, UnaryOperator
		BinaryOperator<Integer> binOp = BinaryOperator.maxBy(Integer::compare);
		out.println(binOp.apply(10, 15));
		
		out.println(Integer.rotateLeft(2, 5));
		out.println(Integer.rotateRight(3, 1));
		
	}

	/**
	 * 
	 */
	@Test
	public void question71(){
		err.println("QUESTION 71");
		ObjIntConsumer<String> consumer = (i, j) ->out.println(Integer.parseInt(i) +j);
		consumer.accept("12", 3);
		
	}

	/**
	 * 
	 */
	@Test
	public void question72(){
		err.println("QUESTION 72");
		Locale loc = new Locale.Builder().setLanguage("zh").setRegion("cn").build();
		out.println("language: " + loc.getLanguage());
		out.println("country: " + loc.getCountry());
		out.println("Display Country: " + loc.getDisplayCountry());
		out.println("Display Country en: " + loc.getDisplayCountry(new Locale("zh", "cn")));
		out.println("Display Language: " + loc.getDisplayLanguage());
		out.println("Display Language en: " + loc.getDisplayLanguage(new Locale("zh", "cn")));
	}
	
	

	/**
	 * 
	 */
	@Test
	public void question78(){
		err.println("QUESTION 78");
		Instant instant = Instant.now();
		instant.plus(1, ChronoUnit.DAYS);
		instant.plus(Duration.ofDays(1));
		instant.plus(Period.ofDays(1));
		instant.plus(1, ChronoUnit.MILLIS);
		instant.plus(Duration.ofMillis(1));
		instant.plusMillis(1);
		
		//following code has compilation error, Instant only has plusHour, plusSeconds, plusMillis and plusNano
		//instant.plusDay(1);
		//instant.plusDuration(1, ChronoUnit.xxx);
		
		//getUnits is not class level method
		out.println(Duration.ofDays(2).plusHours(3).getUnits());//[Seconds, Nanos]
		out.println(Duration.ofDays(2).plusHours(3));//PT51H
		out.println(Duration.ofDays(2).plusHours(3).plusMinutes(2).plusSeconds(4).plusMillis(12));//PT51H2M4.012S
		
		out.println(Period.ofWeeks(2).plusDays(1).getUnits());//[Years, Months, Days]
		out.println(Period.ofWeeks(2).plusDays(1));//P15D
		out.println(Period.ofWeeks(2).plusDays(1).plusYears(2).plusMonths(3));//P2Y3M15D
		
		//following code has compilation error, Period is date-based, no hours, minutes, seconds
		//instant.plus(Period.ofHour(1));
	}

	/**
	 * 
	 */
	@Test
	public void question_80(){
		err.println("QUESTION 80");
		LocalTime time1 = LocalTime.of(10, 10);
		LocalTime time2 = time1.plusHours(9).plusMinutes(50);
		out.println(Duration.between(time1, time2).toHours());//9
		out.println(Duration.between(time2, time1).toMinutes());//-590
		
		LocalDate date1 = LocalDate.of(2017, 4, 3);
		LocalDate date2 = date1.plusDays(20).plusMonths(1);
		out.println(Period.between(date1, date2).toTotalMonths());//1
		out.println(Period.between(date2, date1).toTotalMonths());//-1
		
		LocalDateTime dt1 = LocalDateTime.now();
		LocalDateTime dt2 = dt1.plusDays(30).minusHours(2).plusSeconds(10);
		out.println(Duration.between(dt1, dt2));//PT718H10S
		out.println(Duration.between(dt2, dt1));//PT718H10SPT-718H-10S
	}

	/**
	 * 
	 */
	@Test
	public void question_81(){
		err.println("QUESTION 81");
		Year year = Year.of(2010);
		Month month = Month.APRIL;
		assertEquals(month, Month.of(4));
		YearMonth yearMonth = YearMonth.of(2010, Month.APRIL);
		MonthDay monthDay = MonthDay.of(4, 10);
		assertEquals(yearMonth.atDay(10), monthDay.atYear(year.getValue()));
		DayOfWeek day = DayOfWeek.FRIDAY;
		assertEquals(day, DayOfWeek.of(5));
	}

	/**
	 * 
	 */
	@Test
	public void question(){
		err.println("QUESTION XX");
	}

}
