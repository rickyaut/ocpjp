package demo;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		method1();
	}

	private static void method1() {
	    List<String> strings = Arrays.asList("Ricky", "Nico", "Andrew");
	    
		Comparator<String> comp = (a, b) -> a.length() - b.length();
		
		Collections.sort(strings, comp);
    }

	private static void method2() {
		
	    List<String> strings = Arrays.asList("Ricky", "Nico", "Andrew");
	    
		Collections.sort(strings, (a, b) -> a.length() - b.length());
    }
	
	private static void method3(){
		
		IC v = d -> d.length() > 5;
		
		if(v.isValid("Ricky")){
			System.out.println("Name is valid");
		}else{
			System.out.println("Name is invalid");
		}
		
	}
	
	private static void method4(){
		check("Ricky", d -> d.length() > 5);
	}

	private static void check(String string, IC v) {
	    // TODO Auto-generated method stub
	    
    }

}
