package application;

import javafx.scene.image.Image;

public class Images {
	public static final Image startscreen = new Image("background1.jpg");
	
	public static final Image[] bigTigerMotion = new Image[15];
	public static final Image[] blackTigerMotion = new Image[3];
	
	static {
		for (int i=0; i<bigTigerMotion.length; i++) {
			if(i<10) {
				bigTigerMotion[i] = new Image("frame_0"+i+"_delay-0.07s.png");
			} else {
				bigTigerMotion[i] = new Image("frame_"+i+"_delay-0.07s.png");
			}
		}
		
		for (int i=3;i<blackTigerMotion.length+3;i++) {
			blackTigerMotion[i-3] = new Image("Game1-0"+i+".png", 100, 100, false, false);
			
		}
	}

}
