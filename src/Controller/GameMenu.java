package Controller;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GameMenu extends Scene{
	public static Pane root = new Pane();
	public static String playerName = "Player";
	public static Image playerNameMenu = new Image(ClassLoader.getSystemResource("design/gameinputname/gameinputname.png").toString());
	public GameMenu() {
		super(root,1250,800);
		Canvas canvas = new Canvas(1250, 800);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(playerNameMenu, 0, 0);
		
		Label inputName = new Label("Enter your name and press 'enter'");
		inputName.setFont(Font.font("Cornerstone", FontWeight.NORMAL, 24));
		inputName.setTextFill(Color.GHOSTWHITE);
		inputName.setLayoutX(380);
		inputName.setLayoutY(520);
		
		TextField nameField = new TextField("Player");
		nameField.setFont(Font.font("Cornerstone", FontWeight.BOLD, 36));
		nameField.setAlignment(Pos.CENTER);
		nameField.setPrefSize(600, 100);
		nameField.setLayoutX(1250/2 - 600/2);
		nameField.setLayoutY(800/2 - 100/2 + 50);
		nameField.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			if(key.getCode() == KeyCode.ENTER)
			{
				Main.keySound();
				playerName = nameField.getText();
				System.out.println(playerName + " is ready to play!");
				Main.startGame(playerName);
			}
		});

		root.getChildren().addAll(canvas, nameField, inputName);
		Main.backMenu(this);
	}

}
