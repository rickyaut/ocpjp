package ocpjp;

import static java.lang.System.err;
import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.PosixFileAttributes;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.OptionalInt;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
	 * Correct: we can use Class.forName to register jdbc driver
	 * Correct: We can use DriverManager.registerDriver to register jdbc driver
	 * Wrong: The DriverManager.registerDriver() requires database url, which does not vary depending on our DBMS
	 * Wrong: Using DriverManager.getConnection(), we can register JDBC driver by passing the database URL, which vary depending on our DBMS.
	 */
	@Test
	public void question13() {
		err.println("QUESTION 13");
		try {
			Class.forName("com.mysql.ss");
			DriverManager.registerDriver(new Driver(){

				@Override
				public Connection connect(String url, Properties info) throws SQLException {
					return null;
				}

				@Override
				public boolean acceptsURL(String url) throws SQLException {
					return false;
				}

				@Override
				public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
					return null;
				}

				@Override
				public int getMajorVersion() {
					return 0;
				}

				@Override
				public int getMinorVersion() {
					return 0;
				}

				@Override
				public boolean jdbcCompliant() {
					return false;
				}

				@Override
				public Logger getParentLogger() throws SQLFeatureNotSupportedException {
					return null;
				}});
			DriverManager.getConnection("url");
			DriverManager.getConnection("url", "userr", "password");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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

	/**
	 * 
	 */
	@Test
	public void question_39() {
		err.println("QUESTION 39");
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		long[] threadIDs = threadMXBean.findDeadlockedThreads();
		ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(threadIDs, true, true);
		for(ThreadInfo info : threadInfos){
			out.println(String.format("ThreadID: %s, ThreadName: %s, LockerName: %s, LockOwnerId: %s, LockOwnerName: %s, LockState: %s, BlockedTime: %d", 
					info.getThreadId(), info.getThreadName(), 
					info.getLockName(), info.getLockOwnerId(), info.getLockOwnerName(), 
					info.getThreadState(), info.getBlockedTime()));
		}
	}

	/**
	 * https://docs.oracle.com/javase/tutorial/collections/streams/reduction.html
	 * 
	 * how it works for reduce and collect
	 */
	@Test
	public void question40_41(){
		err.println("QUESTION 40, 41");
		IntStream ints = IntStream.of(11, 2, 7, 32, 4, 8, 21, 9);
		Stream<Integer> stream = Stream.of(11, 2, 7, 32, 4, 8, 21, 9);
		
		//Pay attention to the return type
		//the usage of reduce
		OptionalInt opt = ints.filter(in -> in %2 == 0).reduce(Integer::sum);
		int sum = ints.filter(in -> in % 2 == 0).reduce(0, (a, b) -> a + b);
		sum = stream.reduce(0, (a, b) -> a + b);
		out.println(opt); //OptionalInt[46]
		
		Stream<String> ins = Stream.of("1", "2", "3", "2", "1", "4").distinct();
		Stream<String> ins2 = ins.onClose(new Runnable(){

			@Override
			public void run() {
				out.println("closing");
			}});
		//<R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner)
		String result = ins2.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
		out.println(result);//1234
		ins2.close();
	}

	/**
	 * https://en.wikipedia.org/wiki/Glob_(programming)
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
			PathMatcher pm = FileSystems.getDefault().getPathMatcher("glob:*");//glob or regex
			if(pm.matches(dir.getFileName())){// the method name is matches, not match
				//out.println(dir);
				f_q42 = true;
			}
			return FileVisitResult.CONTINUE;
            //return super.postVisitDirectory(dir, exc);
        }
		
	}
	@Test
	public void question42(){
		err.println("QUESTION 42");
		Path path= Paths.get("");// Technically this is same as Paths.get("."), they are different only when it is printed
		out.println(path.getParent());//getParent returns null when the current path is root
		try {
	        BasicFileAttributes basicAttrs = Files.readAttributes(path, BasicFileAttributes.class);
	        out.println(String.format("%s, %s, %s, %s", /*basicAttrs.fileKey(), */basicAttrs.isSymbolicLink(), basicAttrs.isDirectory(), basicAttrs.isRegularFile(), basicAttrs.isOther()));
	        DosFileAttributes dosAttrs = Files.readAttributes(path, DosFileAttributes.class);
	        out.println(String.format("%s, %s, %s", dosAttrs.isReadOnly(), dosAttrs.isSystem(), dosAttrs.isArchive() ));
			try(Stream<Path> children = Files.list(path)){//children need to be closed, but it is auto-closed in this statement
				children.forEach(out::println);
			}
	        PosixFileAttributes posixAttrs = Files.readAttributes(path, PosixFileAttributes.class); // throws UnsupportedOperationException on Windows
	        out.println(String.format("%s, %s", posixAttrs.owner().toString(), posixAttrs.permissions().toString()));
        } catch (IOException e1) {
        } catch(UnsupportedOperationException e2){ //UnsupportedOperationException is unchecked Exception
        	
        }
		out.println("now in second try catch");
		try {
	        Files.setAttribute(Paths.get("numbers.txt"), "dos:readonly", true); //setAttribute throws IOException
			BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("numbers.txt"));
			try(BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get("writablenumbers.txt"))){
				//file will be created immediately,
				//but nothing will be written to the file until the writer is closed
				//in the try-resource statement, bufferedWriter is auto-closed
				//so at end of this block, it is closed
				bufferedWriter.write("abc");
				bufferedWriter.newLine();
				bufferedWriter.write("Oracle Certified", 10, 3);
				bufferedWriter.newLine();
				bufferedWriter.write("Java Developer");
				bufferedWriter.newLine();
				bufferedWriter.write(80);//character P is written
			}
			DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get("src"));
			directoryStream.forEach(out::println);//src/main, src/test
        } catch (IOException e1) {
	        // TODO Auto-generated catch block
	        e1.printStackTrace();
        }
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
		new StringBuilder().append(new StringBuilder());
	}

}
