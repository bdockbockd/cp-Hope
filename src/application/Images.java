package application;

import javafx.scene.image.Image;

public class Images {
//	public static final Image startscreen = new Image(ClassLoader.getSystemResource("map-01.png").toString(),1250,800,false,false);

//	public static final Image[] blackTigerMotion = new Image[3];
	public static final Image[] blackTigerMotionL = new Image[3];
	public static final Image[] blackTigerMotionR = new Image[3];	
	public static final Image[] blackTigerBasicAttackL = new Image[3];
	public static final Image[] blackTigerBasicAttackR = new Image[3];
	
	public static final Image[] blackTigerMotionL2 = new Image[3];
	public static final Image[] blackTigerMotionR2 = new Image[3];
	public static final Image[] blackTigerBasicAttackL2 = new Image[3];
	public static final Image[] blackTigerBasicAttackR2 = new Image[3];
	
	public static final Image[] blackTigerMotionL3 = new Image[3];
	public static final Image[] blackTigerMotionR3 = new Image[3];
	public static final Image[] blackTigerBasicAttackL3 = new Image[3];
	public static final Image[] blackTigerBasicAttackR3 = new Image[3];

	public static final Image[] jumpAttackL = new Image[2];
	public static final Image[] jumpAttackR = new Image[2];
	public static final Image[] jumpAttackL2 = new Image[2];
	public static final Image[] jumpAttackR2 = new Image[2];
	public static final Image[] jumpAttackL3 = new Image[2];
	public static final Image[] jumpAttackR3 = new Image[2];
	
	public static final Image[] spinAttackL = new Image[3];
	public static final Image[] spinAttackR = new Image[3];
	public static final Image[] spinAttackL2 = new Image[3];
	public static final Image[] spinAttackR2 = new Image[3];
	public static final Image[] spinAttackL3 = new Image[3];
	public static final Image[] spinAttackR3 = new Image[3];
	


	
	public static final Image[] humanMotionL = new Image[1];
	public static final Image[] humanMotionR = new Image[1];
	public static final Image[] stageMap = new Image[3];
	
	public static final Image enemyTomb = new Image(ClassLoader.getSystemResource("design/rip/rip.png").toString(), 70 , 98, false, false);
	
	static {
		for(int i=0;i<stageMap.length;i++) {
			stageMap[i] = new Image(ClassLoader.getSystemResource("design/map/map-0"+(i+1)+".png").toString(),1250,800,false,false);
		}

		humanMotionR[0] = new Image(ClassLoader.getSystemResource("design/enemy1/enemy1-01.png").toString(), 70, 98, false, false);
		humanMotionL[0] = new Image(ClassLoader.getSystemResource("design/enemy1/enemy1-02.png").toString(), 70, 98 , false, false);
		
		for (int i=0;i<blackTigerBasicAttackL.length;i++) {
			blackTigerBasicAttackL[i] = new Image(ClassLoader.getSystemResource("design/bp1/attack/bp1_attack-0"+(i+1)+".png").toString(), 351, 200, false, false);
			blackTigerBasicAttackR[i] = new Image(ClassLoader.getSystemResource("design/bp1/attack/bp1_attack-0"+(i+4)+".png").toString(), 351, 200, false, false);
			blackTigerBasicAttackL2[i] = new Image(ClassLoader.getSystemResource("design/bp2/attack/bp2_attack-0"+(i+1)+".png").toString(), 351, 200, false, false);
			blackTigerBasicAttackR2[i] = new Image(ClassLoader.getSystemResource("design/bp2/attack/bp2_attack-0"+(i+4)+".png").toString(), 351, 200, false, false);
			blackTigerBasicAttackL3[i] = new Image(ClassLoader.getSystemResource("design/bp3/attack/bp3_attack-0"+(i+1)+".png").toString(), 351, 200, false, false);
			blackTigerBasicAttackR3[i] = new Image(ClassLoader.getSystemResource("design/bp3/attack/bp3_attack-0"+(i+4)+".png").toString(), 351, 200, false, false);
		}
		for (int i=0;i<blackTigerMotionL.length;i++) {
			blackTigerMotionL[i] = new Image(ClassLoader.getSystemResource("design/bp1/walk/bp1_walk-0"+(i+1)+".png").toString(), 351, 200, false, false);
			blackTigerMotionR[i] = new Image(ClassLoader.getSystemResource("design/bp1/walk/bp1_walk-0"+(i+4)+".png").toString(), 351, 200, false, false);
			blackTigerMotionL2[i] = new Image(ClassLoader.getSystemResource("design/bp2/walk/bp2_walk-0"+(i+1)+".png").toString(), 351, 200, false, false);
			blackTigerMotionR2[i] = new Image(ClassLoader.getSystemResource("design/bp2/walk/bp2_walk-0"+(i+4)+".png").toString(), 351, 200, false, false);
			blackTigerMotionL3[i] = new Image(ClassLoader.getSystemResource("design/bp3/walk/bp3_walk-0"+(i+1)+".png").toString(), 351, 200, false, false);
			blackTigerMotionR3[i] = new Image(ClassLoader.getSystemResource("design/bp3/walk/bp3_walk-0"+(i+4)+".png").toString(), 351, 200, false, false);
		}
		
		for(int i=0;i<jumpAttackL.length;i++) {
			jumpAttackL[i] = new Image(ClassLoader.getSystemResource("design/bp1/jump/bp1_jump-0"+(i+1)+".png").toString(), 351, 200 ,false , false);
			jumpAttackR[i] = new Image(ClassLoader.getSystemResource("design/bp1/jump/bp1_jump-0"+(i+1)+".png").toString(), 351, 200 ,false , false);
			jumpAttackL2[i] = new Image(ClassLoader.getSystemResource("design/bp2/jump/bp2_jump-0"+(i+1)+".png").toString(), 351, 200 ,false , false);
			jumpAttackR2[i] = new Image(ClassLoader.getSystemResource("design/bp2/jump/bp2_jump-0"+(i+1)+".png").toString(), 351, 200 ,false , false);
			jumpAttackL3[i] = new Image(ClassLoader.getSystemResource("design/bp3/jump/bp3_jump-0"+(i+1)+".png").toString(), 351, 200 ,false , false);
			jumpAttackL3[i] = new Image(ClassLoader.getSystemResource("design/bp3/jump/bp3_jump-0"+(i+1)+".png").toString(), 351, 200 ,false , false);
			
		}
		
		for(int i=0;i<spinAttackL.length;i++) {
			spinAttackL[i] = new Image(ClassLoader.getSystemResource("design/bp1/jump/bp1_jump-0"+(i+1)+".png").toString(), 351, 200 ,false , false);
			spinAttackR[i] = new Image(ClassLoader.getSystemResource("design/bp1/jump/bp1_jump-0"+(i+1)+".png").toString(), 351, 200 ,false , false);
			spinAttackL2[i] = new Image(ClassLoader.getSystemResource("design/bp2/jump/bp2_jump-0"+(i+1)+".png").toString(), 351, 200 ,false , false);
			spinAttackR2[i] = new Image(ClassLoader.getSystemResource("design/bp2/jump/bp2_jump-0"+(i+1)+".png").toString(), 351, 200 ,false , false);
			spinAttackL3[i] = new Image(ClassLoader.getSystemResource("design/bp3/jump/bp3_jump-0"+(i+1)+".png").toString(), 351, 200 ,false , false);
			spinAttackR3[i] = new Image(ClassLoader.getSystemResource("design/bp3/jump/bp3_jump-0"+(i+1)+".png").toString(), 351, 200 ,false , false);
		}
	
		

	}
}
