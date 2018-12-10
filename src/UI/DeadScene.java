package UI;

import java.util.ArrayList;

import Constant.Audio;
import Constant.Images;
import Controller.EnemyGen;
import Controller.LoopGame;
import application.Main;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;

public class DeadScene extends Popup {

	public static ArrayList<Image> deadScene = Images.DEADSCENE;
	public static int selectNumber;

	public DeadScene(String playerName, int score, String time) {
		super();
		selectNumber = 100000;
		Canvas canvas = new Canvas(1136, 567);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(deadScene.get(0), 0, 0);
		this.getContent().addAll(canvas);
		this.setAnchorY(800 / 2 - 1136 / 2 + 350);
		this.setAnchorX(1250 / 2 - 567 / 2 - 190);

		int rank = HallOfFameMenu.getRank(playerName, score);
		int allRank = HallOfFameMenu.getSize();
		scoreFillText(gc, playerName, score, rank, allRank, time);

		this.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			if (this.isShowing()) {
				if (key.getCode() == KeyCode.RIGHT) {
					Audio.SELECTMENU.play();
					selectNumber += 1;
				}
				if (key.getCode() == KeyCode.LEFT) {
					Audio.SELECTMENU.play();
					selectNumber -= 1;
				}
				if (key.getCode() == KeyCode.SPACE || key.getCode() == KeyCode.ENTER) {
					Audio.CHOOSEMENU.play();
					this.hide();
					// -> new StartGame
					if (selectNumber % 2 == 0) {
						selectNumber = 100000;
						GamePause.endGame();
						LoopGame.blackPanther.reset();
						EnemyGen.getbadList().clear();
						Main.startGame(playerName);
					} else {
						selectNumber = 100000;
						Main.mainMenu();
					}
				} else if (key.getCode() == KeyCode.ESCAPE) {
					Audio.CHOOSEMENU.play();
					this.hide();
					selectNumber = 100000;
					Main.gameMenu();
				}
				gc.drawImage(deadScene.get(selectNumber % 2), 0, 0);
				scoreFillText(gc, playerName, score, rank, allRank, time);
			}
		});
	}

	private static void scoreFillText(GraphicsContext gc, String playerName, int score, int rank, int allRank,
			String time) {
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setFill(Color.GHOSTWHITE);
		gc.setFont(Font.font("Cornerstone", 75));
		gc.fillText("YOU'RE KILLED!", 1136 / 2, 140);
		gc.setFont(Font.font("Cornerstone", 47));
		gc.fillText(playerName + "'s score: " + score, 1136 / 2, 220);
		gc.setFont(Font.font("Cornerstone", 30));
		gc.setFill(Color.rgb(255, 245, 0));
		gc.fillText("You are rank " + rank + " from " + allRank + ".", 1136 / 2, 255);
		gc.setFill(Color.GHOSTWHITE);
		gc.fillText("Time: " + time, 1136 / 2, 300);
	}
}
