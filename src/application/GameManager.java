import java.util.ArrayList;
import java.util.List;


public class GameManager {
	
	private static GameManager instance = new GameManager();
	
	private boolean isGameRunning = false;
	private tigerSprite tiger;

	public GameManager() {
	}

	public tigerSprite getPlayer() {
		return tiger;
	}

}
