package Exception;

public class NameFieldException extends Exception {
	
	private static final long serialVersionUID = 4425178354312483007L;
	
	public NameFieldException() {
		System.out.println("Player Name Length must be lower than 10 characters");
	}
}
