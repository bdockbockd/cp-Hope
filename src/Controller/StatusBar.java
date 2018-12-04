package Controller;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class StatusBar extends StackPane {
	private static ProgressBar HPBar;
	private static ProgressIndicator attack;
	private ProgressIndicator pounce;
	private ProgressIndicator spin;
	public StatusBar(String playerName)
	{
		super();
		
		Label playerNameLabel = new Label(playerName);
		playerNameLabel.setStyle("-fx-base: orange; -fx-text-fill: WHITE;");
		playerNameLabel.setFont(Font.font("Cornerstone", FontWeight.BLACK, 24));
		playerNameLabel.setTranslateY(-46);
		//this.setAlignment(Pos.CENTER);
		
		attack = new ProgressIndicator(0);
		attack.setTranslateY(26+31);
		attack.setTranslateX(-84+30);
		attack.setStyle("-fx-text-fill: black;-fx-progress-color: black; -fx-background-insets: 0;-fx-background-radius: 0;");
		
		
		pounce = new ProgressIndicator(0.5);
		pounce.setTranslateY(26+31);
		pounce.setTranslateX(30);
		pounce.setStyle(" -fx-progress-color: black;");
		
		spin = new ProgressIndicator(0.7);
		spin.setTranslateY(26+31);
		spin.setTranslateX(75+30);
		spin.setStyle(" -fx-progress-color: black;");
		
		ImageView statusBG = new ImageView(new Image(ClassLoader.getSystemResource("resources/design/gameplay/gamestatusbar.png").toString()));
		HPBar = new ProgressBar(1);
		HPBar.setPrefSize(460, 18);
		HPBar.setStyle("-fx-accent: rgb(143,194,103);-fx-border-radius: 9px;-fx-text-box-border: black; -fx-control-inner-background: black; -fx-padding: 0px;-fx-background-insets: 4; -fx-background-color:-fx-box-border,linear-gradient(to bottom, derive(-fx-color,100%), derive(-fx-color,100%));");
		HPBar.setTranslateY(-23);
		this.setLayoutX(1250/2-300);
		this.setLayoutY(800-150);
		this.getChildren().addAll(statusBG, HPBar, attack, pounce, spin, playerNameLabel);
	}
	public static void resetProgress()
	{
		attack.setProgress(attack.getProgress()+0.02);
		attackIsReady();
		/*if(attack.getProgress() == 1) {
			attack.setStyle(" -fx-progress-color: green;");
		}*/
		HPBar.setProgress(HPBar.getProgress()-0.005);
	}
	public static boolean attackIsReady() {
		if(attack.getProgress() >= 1){
			System.out.println("attack!");
			attack.setProgress(0);
			return true;
		}
		return attack.getProgress() >= 1;
	}
	public boolean pounceIsReady() {
		return pounce.getProgress() >= 1;
	}
	public boolean spinIsReady() {
		return spin.getProgress() >= 1;
	}
	
}
