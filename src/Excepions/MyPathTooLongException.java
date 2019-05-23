package Excepions;

public class MyPathTooLongException extends MyInvalidPathException{

	public MyPathTooLongException() {
		super("The path is too long");
	}

}
