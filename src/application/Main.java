package application;

import Constant.Audio;
import Constant.FontRes;
import Exception.ExitGameException;
import Exception.HallOfFameException;
import UI.GameMenu;
import UI.HallOfFameMenu;
import UI.HowToPlayMenu;
import UI.MainMenu;
import UI.QuitMenu;
import UI.StartGame;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Main extends Application {

	public static Stage stage;
	public static int selectNumber = 1000000;
	public static MainMenu mainMenu;
	public static GameMenu gameMenu;
	public static StartGame startGame;
	public static HowToPlayMenu howToPlayMenu;
	public static HallOfFameMenu hallOfFameMenu;
	public static QuitMenu quitMenu;

	public void start(Stage primaryStage) {
		try {
			FontRes.load();
			stage = primaryStage;
			stage.setTitle("blackPantherX");
			mainMenu = new MainMenu();
			gameMenu = new GameMenu();
			howToPlayMenu = new HowToPlayMenu();
			hallOfFameMenu = new HallOfFameMenu();
			quitMenu = new QuitMenu();
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent t) {
					gameQuit();
				}
			});
			Audio.MENU_BGM.play();
			mainMenu();
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void mainMenu() {
		stage.setScene(mainMenu);
	}

	public static void gameMenu() {
		stage.setScene(gameMenu);
	}

	public static void howToPlayMenu() {
		stage.setScene(howToPlayMenu);
	}

	public static void hallOfFameMenu() {
		try {
			HallOfFameMenu.fillText();
		} catch (HallOfFameException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("blackPantherX");
			alert.setHeaderText("Hall of fame is updated!");
			alert.showAndWait();
			e.printStackTrace();
		}
		stage.setScene(hallOfFameMenu);
	}

	public static void quitMenu() {
		quitMenu = new QuitMenu();
		MainMenu.pressAble = false;
		quitMenu.show(stage);
	}

	public static void startGame(String playerName) {
		Audio.MENU_BGM.stop();
		startGame = new StartGame(playerName);
		stage.setScene(startGame);
	}

	public static void gameQuit() {
		// get a handle to the stage
		// Stage stage = (Stage) closeButton.getScene().getWindow();
		// do what you have to do
		try {
			HallOfFameMenu.save();
		} catch (ExitGameException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("blackPantherX");
			alert.setHeaderText("THANK FOR PLAYING!");
			alert.setContentText("BEEBER & OUFEOW");
			alert.showAndWait();
		}
		Platform.exit();
		System.exit(0);
	}

	public static void backMenu(Scene s) {
		s.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			if (key.getCode() == KeyCode.ESCAPE) {
				Audio.SELECTMENU.play();
				Main.mainMenu();
			}
		});
	}

}
