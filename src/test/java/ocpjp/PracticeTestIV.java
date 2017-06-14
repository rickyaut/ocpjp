package ocpjp;

import static java.lang.System.err;

import java.util.function.Predicate;

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
	@Test
	public void question(){
		err.println("QUESTION XX");
	}

}
