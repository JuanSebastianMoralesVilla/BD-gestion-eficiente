package custom_exceptions;

@SuppressWarnings("serial")
public class ValuesIsEmptyException extends Exception{
	public ValuesIsEmptyException() {
		super("Somethigs values are Empty");
	}
	
}
