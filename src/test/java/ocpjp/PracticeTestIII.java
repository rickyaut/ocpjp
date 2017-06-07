package ocpjp;

import static java.lang.System.err;
import static java.lang.System.out;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

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
		
		//subMap is method of TreeMap class, not the method of Map interface of HashMap
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
	@Test
	public void question(){
		err.println("QUESTION XX");
	}

}
