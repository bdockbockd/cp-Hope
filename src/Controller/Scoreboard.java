package Controller;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Scoreboard extends StackPane {
	private static int score;
	private static Text scoretxt;
	public Scoreboard(){
		super();
		score = 0;
		
		scoretxt = new Text("Score: "+Integer.toString(score));
		scoretxt.setFill(Color.WHITE);
		scoretxt.setFont(Font.font("Cornerstone", FontWeight.BOLD, 18));
		
		this.setAlignment(Pos.CENTER);
		this.setLayoutX(20);
		this.setLayoutY(20);
		this.getChildren().addAll(scoretxt);
	}
	
	public static int getScore() {
		return score;
	}
	public void setScore(int score)
	{
		Scoreboard.score = score;
		update();
	}
	public void addScore(int score)
	{
		Scoreboard.score += score;
		update();
	}
	public void update() {
		scoretxt.setText("Score: "+Integer.toString(score));
	}
}
