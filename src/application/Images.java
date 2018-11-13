package application;

import javafx.scene.image.Image;

public class Images {
	public static final Image startscreen = new Image(ClassLoader.getSystemResource("map-01.png").toString(),1250,800,false,false);
	public static final Image[] bigTigerMotion = new Image[15];
	public static final Image[] blackTigerMotion = new Image[3];

	static {
		for (int i=0; i<bigTigerMotion.length; i++) {
			if(i<10) {
				bigTigerMotion[i] = new Image(ClassLoader.getSystemResource("frame_0"+i+"_delay-0.07s.png").toString());
			} else {
				bigTigerMotion[i] = new Image(ClassLoader.getSystemResource("frame_"+i+"_delay-0.07s.png").toString());
			}
		}
		
		for (int i=3;i<blackTigerMotion.length+3;i++) {
			blackTigerMotion[i-3] = new Image(ClassLoader.getSystemResource("Game1-0"+i+".png").toString(), 300, 300, false, false);
		}
		
//		for(int i=3; i<)
	}
}
