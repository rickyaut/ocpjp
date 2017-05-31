package ocpjp;

import static java.lang.System.err;
import static java.lang.System.out;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.OptionalInt;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

import org.junit.Test;

public class PracticeTestII {
	public enum Value {
		Value1, Value2();

		// The modifier of enum constructor can only be private
		private Value() {

		}
	}

	/**
	 * How to use Statement and ResultSet to retrieve and manipulate records
	 */
	@Test
	public void question8() {
		err.println("QUESTION 8");
//		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//		ResultSet rs = stmt.executeQuery("SELECT a, b FROM TABLE2");
		// rs will be scrollable, will not show changes made by others,
		// and will be updatable

//		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//		ResultSet rs = stmt.executeQuery("SELECT a, b FROM TABLE2");
		// rs will be scrollable, will not show changes made by others,
		// and will be updatable

//		rs.moveToInsertRow(); // moves cursor to the insert row
//		rs.updateString(1, "AINSWORTH"); // updates the
		// first column of the insert row to be AINSWORTH
//		rs.updateInt(2, 35); // updates the second column to be 35
//		rs.updateBoolean(3, true); // updates the third column to true
//		rs.insertRow();
//		rs.moveToCurrentRow();

	}

	/**
	 * 
	 */
	@Test
	public void question13() {
		err.println("QUESTION 13");
		try {
			Class.forName("com.mysql.ss");
			DriverManager.registerDriver(new Driver(){

				@Override
				public Connection connect(String url, Properties info) throws SQLException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public boolean acceptsURL(String url) throws SQLException {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public int getMajorVersion() {
					// TODO Auto-generated method stub
					return 0;
				}

				@Override
				public int getMinorVersion() {
					// TODO Auto-generated method stub
					return 0;
				}

				@Override
				public boolean jdbcCompliant() {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public Logger getParentLogger() throws SQLFeatureNotSupportedException {
					// TODO Auto-generated method stub
					return null;
				}});
			DriverManager.getConnection("url");
			DriverManager.getConnection("url", "userr", "password");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * 
	 */
	@Test
	public void question16() {
		err.println("QUESTION 16");
		String line = System.console().readLine();
		char[] password = System.console().readPassword();
	}

	/**
	 * There are five constructors in the ConcurrentHashMap class
	 * ConcurrentHashMap(): Creates a new empty map with a default initial capacity(16), load factor(0.75) and concurrencyLevel(16).
	 * ConcurrentHashMap<int initialCapacity): Creates a new empty map with the specified initial capacity, and with default load factor(0.75 and concurrencyLevel(16).
	 * ConcurrentHashMap(int initialCapacity, float loadFactor): Creates a new empty map with the specified initial capacity and load factor and with the default concurrencyLevel(16).
	 * ConcurrentHashMap(int initialCapacity, float loadFactor, int concurrencyLevel): Creates a new empty map with the specified initial capacity, load factor and concurrency level.
	 * ConcurrentHashMap(Map<?extends K, ? extends V> m): Creates a new map with the same mappings as the given map.
 --------------- ------------------- ------------------- --------------------- 
|   Property    |     HashMap       |    Hashtable      |  ConcurrentHashMap  |
 --------------- ------------------- ------------------- ---------------------  
|      Null     |     allowed       |              not allowed                |
|  values/keys  |                   |                                         |
 --------------- ------------------- ----------------------------------------- 
|Is thread-safe |       no          |                  yes                    |
 --------------- ------------------- ------------------- --------------------- 
|     Lock      |       not         | locks the whole   | locks the portion   |        
|  mechanism    |    applicable     |       map         |                     | 
 --------------- ------------------- ------------------- --------------------- 
|   Iterator    |               fail-fast               |       fail-safe     | 
 --------------- --------------------------------------- --------------------- 
	 */
	@Test
	public void question33(){
		err.println("QUESTION 33");
		ConcurrentHashMap<String, Integer> map = null;
		map = new ConcurrentHashMap<String, Integer>();//initial table size is 16
		map = new ConcurrentHashMap<String, Integer>(10);
	}

	@Test
	public void question40_41(){
		err.println("QUESTION 40, 41");
		IntStream ints = IntStream.of(11, 2, 7, 32, 4, 8, 21, 9);
		
		//Pay attention to the return type
		//the usage of reduce
		OptionalInt opt = ints.filter(in -> in %2 == 0).reduce(Integer::sum);
		out.println(opt); //OptionalInt[46]
		
		Stream<String> ins = Stream.of("1", "2", "3", "2", "1", "4").distinct();
		//<R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner)
		String result = ins.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
		out.println(result);//1234
	}

	/**
	 * 
	 */
	static boolean f_q42;
	final class Search extends SimpleFileVisitor<Path>{

		@Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            return super.preVisitDirectory(dir, attrs);
        }

		@Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            return super.visitFile(file, attrs);
        }

		@Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            return super.visitFileFailed(file, exc);
        }

		@Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
			Pattern p = Pattern.compile("...");
			PathMatcher pm = FileSystems.getDefault().getPathMatcher("glob:...");
			if(pm.matches(dir.getFileName())){
				out.println(dir);
				f_q42 = true;
			}
			return FileVisitResult.CONTINUE;
            //return super.postVisitDirectory(dir, exc);
        }
		
	}
	@Test
	public void question42(){
		err.println("QUESTION 42");
		Path path= Paths.get(".");
		FileVisitor<Path> searcher = new Search();
		try {
	        Files.walkFileTree(path, searcher);
			if(!f_q42)out.println("No matches");
        } catch (IOException e) {
	        e.printStackTrace();
        }
	}

	/**
	 * 
	 */
	@Test
	public void question() {
		err.println("QUESTION XX");
	}

}
