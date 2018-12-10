package Controller;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ScoreBoard extends StackPane {

	private static int score;
	private static Text scoretxt;
	private static boolean isHide;

	public ScoreBoard() {
		super();
		score = 0;
		isHide = false;
		scoretxt = new Text("Score: " + Integer.toString(score));
		scoretxt.setFill(Color.WHITE);
		scoretxt.setFont(Font.font("Cornerstone", FontWeight.BOLD, 18));

		this.setAlignment(Pos.CENTER);
		this.setLayoutX(20);
		this.setLayoutY(20);
		this.getChildren().addAll(scoretxt);
	}

	public static boolean isHide() {
		return isHide;
	}

	public static void hide() {
		isHide = true;
		update();
	}

	public static void show() {
		isHide = false;
		update();
	}

	public static void reset() {
		setScore(0);
	}

	public static int getScore() {
		return score;
	}

	public static void setScore(int newScore) {
		score = newScore;
		update();
	}

	public static void addScore(int addScore) {
		score += addScore;
		update();
	}

	public static void update() {
		scoretxt.setText("Score: " + Integer.toString(score));
		if (isHide) {
			scoretxt.setText(null);
		}
	}
}
