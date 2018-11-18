package application;

import javafx.scene.image.Image;

public class Images {
//	public static final Image startscreen = new Image(ClassLoader.getSystemResource("map-01.png").toString(),1250,800,false,false);

//	public static final Image[] blackTigerMotion = new Image[3];
	public static final Image[] blackTigerMotionL = new Image[3];
	public static final Image[] blackTigerMotionR = new Image[3];
	public static final Image[] blackTigerBasicAttackL = new Image[3];
	public static final Image[] blackTigerBasicAttackR = new Image[3];


	public static final Image[] stageMap = new Image[3];
	
	
	static {
		
		for (int i=0;i<blackTigerBasicAttackL.length;i++) {
			blackTigerBasicAttackL[i] = new Image("resources/BlackPantherDesign/greenBlackPanther/attack/attackingGreenBlackPanther-0"+(i+1)+".png", 351, 200, false, false);
			blackTigerBasicAttackR[i] = new Image("resources/BlackPantherDesign/greenBlackPanther/attack/attackingGreenBlackPanther-0"+(i+4)+".png", 351, 200, false, false);

		}
		
		for (int i=0;i<blackTigerMotionL.length;i++) {
			blackTigerMotionL[i] = new Image("BlackPantherDesign/greenBlackPanther/walkingGreenBlackPanther/walkingGreenBlackPanther-0"+(i+1)+".png", 351, 200, false, false);
			blackTigerMotionR[i] = new Image("BlackPantherDesign/greenBlackPanther/walkingGreenBlackPanther/walkingGreenBlackPanther-0"+(i+4)+".png", 351, 200, false, false);
		}
		
		for(int i=0;i<stageMap.length;i++) {
			stageMap[i] = new Image(("BlackPantherDesign/fullMap/fullMap-0"+(i+1)+".png").toString(),1250,800,false,false);
		}
	}
}
