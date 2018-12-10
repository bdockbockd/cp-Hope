package UI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Constant.Audio;
import Constant.Images;
import Controller.Main;
import Exception.ExitGameException;
import Exception.HallOfFameException;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Pair;

public class HallOfFameMenu extends Scene {

	private static List<Pair<String, Integer>> playerDataList;
	private static Image hallOfFameBG = Images.hallOfFameBG;
	public static Pane root;
	private static Canvas canvas;
	private static GraphicsContext gc;
	private static boolean isUpdate;
	private static final int HALLOFFAMENUMBER = 7;
	// private static File file = new File("resources/HallOfFame.txt");

	public HallOfFameMenu() {
		super(new Pane(), 1250, 800);
		root = (Pane) super.getRoot();
		canvas = new Canvas(1250, 800);
		gc = canvas.getGraphicsContext2D();
		playerDataList = new ArrayList<Pair<String, Integer>>();
		isUpdate = false;

		readHallOfFame();
		try {
			fillText();
		} catch (HallOfFameException e) {
			// TODO Auto-generated catch block
			// Alert alert = new Alert(AlertType.INFORMATION);
			// alert.setTitle("Hall of fame");
			// alert.setHeaderText("Hall of fame is updated!");
			// alert.showAndWait();
			// alert.setContentText("Could not find file blabla.txt!");
			// e.printStackTrace();
			// System.out.println("Hall of Fame fillText initialize!");
		}

		root.getChildren().addAll(canvas);
		Main.backMenu(this);
		this.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			if (key.getCode() == KeyCode.SPACE || key.getCode() == KeyCode.ENTER) {
				Audio.SELECTMENU.play();
				Main.mainMenu();
			}
		});
	}

	public static void fillText() throws HallOfFameException {
		gc.drawImage(hallOfFameBG, 0, 0);
		gc.setFont(Font.font("Cornerstone", FontWeight.SEMI_BOLD, 36));
		gc.setFill(Color.GHOSTWHITE);
		for (int i = 0; i < HALLOFFAMENUMBER && i < playerDataList.size(); i++) {
			gc.fillText(
					"Rank " + (i + 1) + ": " + playerDataList.get(i).getKey() + " " + playerDataList.get(i).getValue(),
					434, 218 + 84 * i);
		}
		if (isUpdate == true) {
			isUpdate = false;
			throw new HallOfFameException("Hall of Fame is update!");
		}
	}

	public static void save() throws ExitGameException {
		try {
			PrintWriter fw = new PrintWriter("resources/HallOfFame.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 0; i < playerDataList.size(); i++) {
				bw.write(playerDataList.get(i).getKey() + ":" + playerDataList.get(i).getValue());
				bw.newLine();
			}
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		throw new ExitGameException();
	}

	public static int getSize() {
		return playerDataList.size();
	}

	private static void addList(String playerName, int score) {
		playerDataList.add(new Pair(playerName, score));
		sortList();
	}

	public static int getRank(String playerName, int score) {
		int rank = playerDataList.size();
		for (int i = playerDataList.size() - 1; i >= 0 && score > playerDataList.get(i).getValue(); i--) {
			rank = i;
		}
		if (rank + 1 <= HALLOFFAMENUMBER) {
			isUpdate = true;
		}
		addList(playerName, score);
		return rank + 1;
	}

	private static void sortList() {
		Collections.sort(playerDataList, new Comparator<Pair<String, Integer>>() {
			@Override
			public int compare(final Pair<String, Integer> o1, final Pair<String, Integer> o2) {
				if (o1.getValue() > o2.getValue()) {
					return -1;
				} else {
					return 1;
				}
			}
		});
	}

	public static void readHallOfFame() {
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("resources/HallOfFame.txt")))) {
			String line;
			while ((line = reader.readLine()) != null) {
				int colonIndex = line.lastIndexOf(":");
				String playerName = line.substring(0, colonIndex);
				int score = Integer.parseInt(line.substring(colonIndex + 1, line.length()));
				addList(playerName, score);
				// System.out.println(playerName+":"+score);
			}
			sortList();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}