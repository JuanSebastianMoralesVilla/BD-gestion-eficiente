package custom_exceptions;

@SuppressWarnings("serial")
public class InvalidValueException extends Exception{
	public InvalidValueException() {
		super("Invalid values, please check");
	}
}
