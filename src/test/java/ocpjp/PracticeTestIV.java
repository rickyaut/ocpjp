package ocpjp;

import static java.lang.System.err;
import static java.lang.System.out;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

import org.junit.Test;

public class PracticeTestIV {

	/**
	 * 
	 */
	@Test
	public void question2(){
		Predicate<String> p = (String s) ->s.startsWith("a");
		err.println("QUESTION 2");
	}

	/**
	 * 
	 */
	enum Month{
		January(31),
		Febuary(28){
			public int getDays(int year){
				return year%4 == 0 ? 29 : this.days;
			}
		};
		
		protected int days;
		
		private Month(int days){
			this.days = days;
		}
		public int getDays(int year){
			return days;
		}
	}
	/**
	 * How  to use RowSetFactory
	 * @throws SQLException
	 */
	public void question11() throws SQLException {
		RowSetFactory rowsetFactory = RowSetProvider.newFactory();
		//There are 4 methods to create JdbcRowSet object
		//1. Use JdbcRowSetImpl class's constructor that takes a RowSet object
		//2. Use JdbcRowSetImpl class's constructor that takes a Connection object
		//3. Use JdbcRowSetImpl default constructor
		//4. Use instance of RowSetFactory, which is created from RowSetProvider
		JdbcRowSet jrs = rowsetFactory.createJdbcRowSet();
		jrs.setCommand("SELECT First_Name, Last_Name, Age FROM TITLES WHERE TYPE = ?");
		jrs.setUrl("jdbc:myDriver:myAttribute");
		jrs.setUsername("cervantes");
		jrs.setPassword("sancho");
		jrs.setString(1, "BIOGRAPHY");
		out.println(jrs.getString("First_Name"));
		out.println(jrs.getString(1));
		jrs.execute();
		
	}

	/**
	 * How to use CachedRowSet
	 * The order of exceptions declared in throws statement does not matter, 
	 * child exception and super-class exception can be declared at the same time
	 * @throws SQLException 
	 */
	public void question13() throws SQLException, FileNotFoundException, IOException {
		err.println("QUESTION 13");
		//assume we have 
		Connection con = null;
		ResultSet rs= con.createStatement().executeQuery("SELECT ID from allstar");
		CachedRowSet crs = null;//new com.sun.rowset.CachedRowSetImpl();
		crs.populate(rs);
		crs.absolute(3);
		crs.updateInt(1,  42);
		crs.updateRow();
		//The update operation only after calling acceptChangess
		crs.acceptChanges();
		con.commit();
	}

	/**
	 * 
	 */
	@Test
	public void question25() {
		err.println("QUESTION 25");
		List<Integer> ints = Arrays.asList(2, 4, 6, 8, 10);
		//how to convert stream to list
		//removeAll take a collection as parameter
		//return true if something is removed
		boolean result1 = ints.removeAll(ints.stream().filter(i -> i < 3).collect(Collectors.toList()));
		
		boolean result2 = ints.removeIf(i -> i< 7);
	}

	/**
	 * 
	 */
	@Test
	public void question() {
		err.println("QUESTION XX");
	}

}
