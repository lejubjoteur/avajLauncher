package src;

public class MyFileException extends Exception {
	public MyFileException(String s) {
		super("Error : " + s);
	}
}
