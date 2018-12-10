package UI;

import java.util.ArrayList;

import Constant.Audio;
import Constant.Images;
import Controller.Main;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class MainMenu extends Scene {

	public static ArrayList<Image> selectMenu = Images.selectMenu;
	public static int selectNumber;
	public static Pane root;
	public static boolean pressAble;

	public MainMenu() {
		super(new Pane(), 1250, 800);
		selectNumber = 1000000;
		root = (Pane) super.getRoot();
		pressAble = true;

		Canvas canvas = new Canvas(1250, 800);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(selectMenu.get(0), 0, 0);
		root.getChildren().add(canvas);

		this.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			if (!Main.quitMenu.isShowing() && pressAble == false) {
				pressAble = true;
			} else if (!Main.quitMenu.isShowing()) {
				if (key.getCode() == KeyCode.DOWN) {
					Audio.SELECTMENU.play();
					selectNumber += 1;
				}
				if (key.getCode() == KeyCode.UP) {
					Audio.SELECTMENU.play();
					selectNumber -= 1;
				}
				if (key.getCode() == KeyCode.ESCAPE) {
					Audio.SELECTMENU.play();
					Main.quitMenu();
				}
				if (key.getCode() == KeyCode.SPACE || key.getCode() == KeyCode.ENTER) {
					// System.out.println(selectNumber%4);
					Audio.CHOOSEMENU.play();
					switch (selectNumber % 4) {
					case 0:
						Main.gameMenu();
						break;
					case 1:
						Main.howToPlayMenu();
						break;
					case 2:
						Main.hallOfFameMenu();
						break;
					case 3:
						Main.quitMenu();
						break;
					}
				}
				gc.drawImage(selectMenu.get(selectNumber % 4), 0, 0);
			}
		});
	}

}
