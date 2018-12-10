package UI;

import java.util.ArrayList;

import Constant.Audio;
import Constant.Images;
import application.Main;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Popup;

public class QuitMenu extends Popup {

	public static ArrayList<Image> quitMenu = Images.QUITMENU;
	public static int selectNumber = 100000;

	public QuitMenu() {
		super();
		Canvas canvas = new Canvas(600, 300);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(quitMenu.get(0), 0, 0);
		this.getContent().addAll(canvas);
		this.setAnchorY(800 / 2 - 80);
		this.setAnchorX(1250 / 2 - 200);

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
					Audio.SELECTMENU.play();
					if (selectNumber % 2 == 0) {
						Main.gameQuit();
					} else {
						selectNumber = 100000;
						this.hide();
					}
				} else if (key.getCode() == KeyCode.ESCAPE) {
					Audio.SELECTMENU.play();
					MainMenu.pressAble = true;
					selectNumber = 100000;
				}
				gc.drawImage(quitMenu.get(selectNumber % 2), 0, 0);
			}
		});
	}

}