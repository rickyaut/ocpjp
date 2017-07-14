package demo;

public interface IA {
	public String getMessage();
	
	public default void showMessage(){
		System.out.println(getMessage());
	}
}
