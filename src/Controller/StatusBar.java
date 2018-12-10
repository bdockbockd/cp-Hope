package Controller;

import Constant.Images;
import Sprite.BlackPanther;
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
	private static ProgressIndicator pounce;
	private static ProgressIndicator spin;
	private static Label playerNameLabel;
	private static final int coolDownSize = 100;
	
	public StatusBar(String playerName)
	{
		super();  
		playerNameLabel = new Label(playerName);
		playerNameLabel.setStyle("-fx-text-fill: WHITE;");
		playerNameLabel.setFont(Font.font("Cornerstone", FontWeight.BLACK, 24));
		playerNameLabel.setTranslateY(-46);
		
		attack = new ProgressIndicator(0); 
		attack.setTranslateY(26+31);
		attack.setTranslateX(-84+30);
		attack.setStyle("-fx-text-fill: black;-fx-progress-color: black; -fx-background-insets: 0;-fx-background-radius: 0;");
//		attack.setMinSize(coolDownSize, coolDownSize);
//		attack.setMaxSize(coolDownSize, coolDownSize);
		attack.setPrefSize(coolDownSize, coolDownSize);
		
		
		pounce = new ProgressIndicator(0);
		pounce.setTranslateY(26+31);
		pounce.setTranslateX(30);
		pounce.setStyle(" -fx-progress-color: black;");
//		pounce.setMinSize(coolDownSize, coolDownSize);
//		pounce.setMaxSize(coolDownSize, coolDownSize);
		pounce.setPrefSize(coolDownSize, coolDownSize);
		
		spin = new ProgressIndicator(0);
		spin.setTranslateY(26+31);
		spin.setTranslateX(75+30);
		spin.setStyle(" -fx-progress-color: black;");
		spin.setPrefSize(10, 10);
//		spin.setMinSize(coolDownSize, coolDownSize);
//		spin.setMaxSize(coolDownSize, coolDownSize);
		spin.setPrefSize(coolDownSize, coolDownSize);
		
		ImageView statusBG = new ImageView(Images.STATUSBAR);
		HPBar = new ProgressBar(1);
		HPBar.setPrefSize(460, 18);
		HPBar.setStyle("-fx-accent: rgb(143,194,103);-fx-border-radius: 9px;-fx-text-box-border: black; -fx-control-inner-background: black; -fx-padding: 0px;-fx-background-insets: 4; -fx-background-color:-fx-box-border,linear-gradient(to bottom, derive(-fx-color,100%), derive(-fx-color,100%));");
		HPBar.setTranslateY(-23);
		
		this.setLayoutX(1250/2-300);
		this.setLayoutY(20); //800-150
		this.getChildren().addAll(statusBG, HPBar, attack, pounce, spin, playerNameLabel);
	}
	public static void reset(String playerName) {
		playerNameLabel.setText(playerName);
	}
	public static void resetProgress(BlackPanther blackPanther)
	{
		double attackRate = 0;
		double pounceRate = 0;
		double spinRate = 0;
		if(blackPanther.getStatus() == 0)
		{
			attackRate = 0.2;
			pounceRate = 0.015;
			spinRate = 0.01;
		}
		else if(blackPanther.getStatus() == 1)
		{
			attackRate = 0.3;
			pounceRate = 0.020;
			spinRate = 0.03;
		}
		else if(blackPanther.getStatus() == 2)
		{
			attackRate = 0.25;
			pounceRate = 0.02;
			spinRate = 0.02;
		}
		attack.setProgress(attack.getProgress()+attackRate);
		pounce.setProgress(pounce.getProgress()+pounceRate);
		spin.setProgress(spin.getProgress()+spinRate);
		HPBar.setProgress(blackPanther.getHealth()/blackPanther.getMaxHealth());
	}
	public static boolean attackIsReady() {
		if(attack.getProgress() >= 1){
			System.out.println("attack!");
			attack.setProgress(0);
			return true;
		}
		return attack.getProgress() >= 1;
	}
	public static boolean pounceIsReady() {
		if(pounce.getProgress() >= 1){
			pounce.setProgress(0);
			return true;
		}
		return pounce.getProgress() >= 1;
	}
	public static boolean spinIsReady() {
		if(spin.getProgress() >= 1){
			spin.setProgress(0);
			return true;
		}
		return spin.getProgress() >= 1;
	}
}
