package Exception;

public class GameOverException extends Exception {

	private static final long serialVersionUID = -3361896826591043910L;

	public GameOverException() {
		System.out.println("GAME OVER!");
	}
}
