package application;

import javafx.scene.image.Image;

public class Images {
//	public static final Image startscreen = new Image(ClassLoader.getSystemResource("map-01.png").toString(),1250,800,false,false);

//	public static final Image[] blackTigerMotion = new Image[3];
	public static final Image[] blackTigerMotionL = new Image[3];
	public static final Image[] blackTigerMotionR = new Image[3];
	public static final Image[] blackTigerBasicAttackL = new Image[3];
	public static final Image[] blackTigerBasicAttackR = new Image[3];
	
	public static final Image[] humanMotionL = new Image[1];
	public static final Image[] humanMotionR = new Image[1];
	public static final Image[] stageMap = new Image[3];
	
	static {

		humanMotionR[0] = new Image(ClassLoader.getSystemResource("resources/design/enemy1/enemy1-01.png").toString(), 70, 98, false, false);
		humanMotionL[0] = new Image(ClassLoader.getSystemResource("resources/design/enemy1/enemy1-02.png").toString(), 70, 98 , false, false);
		
		for (int i=0;i<blackTigerBasicAttackL.length;i++) {
			blackTigerBasicAttackL[i] = new Image(ClassLoader.getSystemResource("resources/design/bp1/attack/bp1_attack-0"+(i+1)+".png").toString(), 351, 200, false, false);
			blackTigerBasicAttackR[i] = new Image(ClassLoader.getSystemResource("resources/design/bp1/attack/bp1_attack-0"+(i+4)+".png").toString(), 351, 200, false, false);
		}
		for (int i=0;i<blackTigerMotionL.length;i++) {
			blackTigerMotionL[i] = new Image(ClassLoader.getSystemResource("resources/design/bp1/walk/bp1_walk-0"+(i+1)+".png").toString(), 351, 200, false, false);
			blackTigerMotionR[i] = new Image(ClassLoader.getSystemResource("resources/design/bp1/walk/bp1_walk-0"+(i+4)+".png").toString(), 351, 200, false, false);
		}
		
		for(int i=0;i<stageMap.length;i++) {
			stageMap[i] = new Image(ClassLoader.getSystemResource("resources/design/map/map-0"+(i+1)+".png").toString(),1250,800,false,false);
		}
	}
}
