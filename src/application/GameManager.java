import java.util.ArrayList;
import java.util.List;

import Sprite.tigerSprite;


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
