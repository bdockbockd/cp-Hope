package Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import application.Images;
import application.Audio;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.DirectoryChooser;
import javafx.util.Pair;

public class HallOfFameMenu extends Scene{
	
	private static List<Pair<String, Integer>> playerDataList = new ArrayList<Pair<String, Integer>>();
	private static Image hallOfFameBG = Images.hallOfFameBG;
	public static Pane root = new Pane();
	private static Canvas canvas = new Canvas(1250, 800);
	private static GraphicsContext gc = canvas.getGraphicsContext2D();
	
	public HallOfFameMenu() {
		super(root,1250,800);
//		Canvas canvas = new Canvas(1250, 800);
//		GraphicsContext gc = canvas.getGraphicsContext2D();
		readHallOfFame();
		//FillText(gc);
		root.getChildren().addAll(canvas);
		Main.backMenu(this);
		this.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
	      if(key.getCode()==KeyCode.SPACE || key.getCode() == KeyCode.ENTER || key.getCode() == KeyCode.ALT || key.getCode() == KeyCode.CONTROL || key.getCode() == KeyCode.COMMAND)
	      {
	    	  Audio.SELECTMENU.play();
	    	  Main.mainMenu();
	      }
		});
	}
	
	public static void fillText() {
		gc.drawImage(hallOfFameBG, 0, 0);
		gc.setFont(Font.font("Cornerstone", FontWeight.SEMI_BOLD, 36));
		gc.setFill(Color.GHOSTWHITE);
		for(int i = 0;i < 7 && i < playerDataList.size();i++){
			gc.fillText("Rank "+(i+1)+": "+playerDataList.get(i).getKey()+" "+playerDataList.get(i).getValue(), 434, 218+84*i);
		}
	}
	
	public static void save() {      
		try {
			PrintWriter fw = new PrintWriter("resources/HallOfFame.txt");
			BufferedWriter bw = new BufferedWriter(fw);
	        for(int i = 0;i < playerDataList.size();i++)
	        {
	        	bw.write(playerDataList.get(i).getKey()+":"+playerDataList.get(i).getValue());
	        	bw.newLine();
	        }
	        bw.close();
	        fw.close();
		} catch (IOException e) {
	        e.printStackTrace();
	    }
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
		for(int i = playerDataList.size()-1;i>=0&&score>playerDataList.get(i).getValue();i--){
			rank = i;
		}
		addList(playerName, score);
		return rank+1;
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
	
	public void readHallOfFame() {
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("resources/HallOfFame.txt")))) {
	        String line;
	        
	        while ((line = reader.readLine()) != null)
	        {
	        	int colonIndex = line.lastIndexOf(":");
	        	String playerName = line.substring(0, colonIndex);
	        	int score = Integer.parseInt(line.substring(colonIndex+1, line.length()));
	        	addList(playerName, score);
	        }
	        sortList();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}