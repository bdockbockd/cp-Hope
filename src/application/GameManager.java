import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
import Sprite.tigerSprite;
=======
import TigerSpirte.tigerSprite;
>>>>>>> 34dbf3a153f997c0a3c6c3eb1734167854cfee33


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
