package application;

public class GameManager {
	
	private static GameManager instance = new GameManager();
	
	private boolean isGameRunning = false;

	public static void createHumanBot() {
		
	}
	public GameManager() {
		
	}
	public static GameManager getInstance() {
		return instance;
	}
	public static void setInstance(GameManager instance) {
		GameManager.instance = instance;
	}

}
