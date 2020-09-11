
public class EmptyStackException extends RuntimeException { // exception

	public EmptyStackException(String message) {
		
		super ("The " + message + " is empty.");
		
	}	
}

