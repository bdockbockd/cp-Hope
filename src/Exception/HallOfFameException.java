package Exception;

public class HallOfFameException extends Exception {
	
	private static final long serialVersionUID = 9045970519910759269L;
	
	public HallOfFameException(String playerName) {
		System.out.println("Hall of Fame is updated! "+playerName);
	}
}
