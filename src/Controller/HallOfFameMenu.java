package Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
import javafx.stage.DirectoryChooser;
import javafx.util.Pair;

public class HallOfFameMenu extends Scene{
	private static List<Pair<String, Integer>> ranked = new ArrayList<Pair<String, Integer>>();
	//private static final Comparator<Pair<String, Integer>> c = reverseOrder(comparing(Pair::getValue));
	
	private static Image hallOfFameBG = new Image(ClassLoader.getSystemResource("design/halloffame/halloffame.png").toString());
	public static Pane root = new Pane();
	//public static Image playerNameMenu = new Image(ClassLoader.getSystemResource("GameLebel-01.png").toString());
	public HallOfFameMenu() {
		super(root,1250,800);
		Canvas canvas = new Canvas(1250, 800);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(hallOfFameBG, 0, 0);
		//writeNewUser();
		readHallOfFame();
		gc.setFont(Font.font("Cornerstone", FontWeight.SEMI_BOLD, 36));
		gc.setFill(Color.GHOSTWHITE);
		for(int i = 0;i < 7 && i < ranked.size();i++)
		{
			gc.fillText("Ranked "+(i+1)+": "+ranked.get(i).getKey()+" "+ranked.get(i).getValue(), 434, 218+84*i);
		}
		root.getChildren().addAll(canvas);
		Main.backMenu(this);
		this.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
		      if(key.getCode()==KeyCode.SPACE || key.getCode() == KeyCode.ENTER || key.getCode() == KeyCode.ALT || key.getCode() == KeyCode.CONTROL || key.getCode() == KeyCode.COMMAND)
		      {
		    	  Main.keySound();
		    	  Main.mainMenu();
		      }
		});
	}
	
	public void writeNewUser() {      
        PrintWriter fw = null;
        try {
            fw = new PrintWriter("res/HallOfFame.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("hhhu:100000");
            bw.newLine();
            bw.write("NoobBee:20");
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void readHallOfFame() {
		//System.out.println("readHallOfFame");
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("resources/HallOfFame.txt")))) {
	        String line;
	        
	        int count = 0;
	        while ((line = reader.readLine()) != null)
	        {
	        	int colonIndex = line.lastIndexOf(":");
	        	String player = line.substring(0, colonIndex);
	        	int score = Integer.parseInt(line.substring(colonIndex+1, line.length()));
	        	ranked.add(new Pair(player, score));
	        	count++;
	        }
	        
	        Collections.sort(ranked, new Comparator<Pair<String, Integer>>() {
	            @Override
	            public int compare(final Pair<String, Integer> o1, final Pair<String, Integer> o2) {
	            	if (o1.getValue() > o2.getValue()) {
	                    return -1;
	                } else if (o1.getValue().equals(o2.getValue())) {
	                    return 0; // You can change this to make it then look at the
	                              //words alphabetical order
	                } else {
	                    return 1;
	                }
	            }
	        });
	        
	        PrintWriter fw = new PrintWriter("resources/HallOfFame.txt");
	        BufferedWriter bw = new BufferedWriter(fw);
	        for(int i = 0;i < ranked.size();i++)
	        {
	        	bw.write(ranked.get(i).getKey()+":"+ranked.get(i).getValue());
	        	bw.newLine();
	        }
	        //writeHallOfFame
	        //bw.write("BEE"+":"+10000);
	        bw.close();
	        fw.close();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}