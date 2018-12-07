package Constant;

import java.util.ArrayList;
import java.util.List;

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

	public static final Image[] jumpAttackL = new Image[1];
	public static final Image[] jumpAttackR = new Image[1];
	public static final Image[] jumpAttackL2 = new Image[1];
	public static final Image[] jumpAttackR2 = new Image[1];
	public static final Image[] jumpAttackL3 = new Image[1];
	public static final Image[] jumpAttackR3 = new Image[1];
	
	public static final Image[] spinAttackL = new Image[2];
	public static final Image[] spinAttackR = new Image[2];
	public static final Image[] spinAttackL2 = new Image[2];
	public static final Image[] spinAttackR2 = new Image[2]; 
	public static final Image[] spinAttackL3 = new Image[2];
	public static final Image[] spinAttackR3 = new Image[2];
	
	public static final Image[] humanMotionL = new Image[1];
	public static final Image[] humanMotionR = new Image[1];
	public static final Image[] stageMap = new Image[3];
	public static final List<List<Image[]>> STAGETIGER = new ArrayList<List<Image[]>>();
	public static final List<Image[]> STAGE1 = new ArrayList<Image[]>();
	public static final List<Image[]> STAGE2 = new ArrayList<Image[]>();
	public static final List<Image[]> STAGE3 = new ArrayList<Image[]>();

	
	public static final Image enemyTomb = new Image(ClassLoader.getSystemResource("design/rip/rip.png").toString(), 70 , 98, false, false);
	
	public static final Image healthPotion = new Image(ClassLoader.getSystemResource("design/item/item-01.png").toString(), 67, 66, false ,false);
	public static final Image superPotion = new Image(ClassLoader.getSystemResource("design/item/item-02.png").toString(), 67, 66, false ,false);
	public static final Image meat = new Image(ClassLoader.getSystemResource("design/item/item-03.png").toString(), 67, 66, false ,false);
	
	public static final Image playerNameMenu = new Image(ClassLoader.getSystemResource("design/gameinputname/gameinputname.png").toString());
	public static final Image hallOfFameBG = new Image(ClassLoader.getSystemResource("design/halloffame/halloffame.png").toString());
	public static final Image howToPlayImage = new Image(ClassLoader.getSystemResource("design/howtoplay/howtoplay.png").toString());
	public static final Image STATUSBAR = new Image(ClassLoader.getSystemResource("design/statusbar/gamestatusbar.png").toString());
	public static final ArrayList<Image> selectMenu = new ArrayList<Image>();
	public static final ArrayList<Image> quitMenu = new ArrayList<Image>();
	public static final ArrayList<Image> gamePause = new ArrayList<Image>();
	public static final ArrayList<Image> deadScene = new ArrayList<Image>();
	
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
			jumpAttackL[i] = new Image(ClassLoader.getSystemResource("design/bp1/jump/bp1_jump-0"+(2)+".png").toString(), 351, 200 ,false , false);
			jumpAttackR[i] = new Image(ClassLoader.getSystemResource("design/bp1/jump/bp1_jump-0"+(4)+".png").toString(), 351, 200 ,false , false);
			jumpAttackL2[i] = new Image(ClassLoader.getSystemResource("design/bp2/jump/bp2_jump-0"+(2)+".png").toString(), 351, 200 ,false , false);
			jumpAttackR2[i] = new Image(ClassLoader.getSystemResource("design/bp2/jump/bp2_jump-0"+(4)+".png").toString(), 351, 200 ,false , false);
			jumpAttackL3[i] = new Image(ClassLoader.getSystemResource("design/bp3/jump/bp3_jump-0"+(2)+".png").toString(), 351, 200 ,false , false);
			jumpAttackL3[i] = new Image(ClassLoader.getSystemResource("design/bp3/jump/bp3_jump-0"+(4)+".png").toString(), 351, 200 ,false , false);
			
		}
		
		for(int i=0;i<spinAttackL.length;i++) {
			spinAttackL[i] = new Image(ClassLoader.getSystemResource("design/bp1/spin/bp1_spin-0"+(i+2)+".png").toString(), 351, 200 ,false , false);
			spinAttackR[i] = new Image(ClassLoader.getSystemResource("design/bp1/spin/bp1_spin-0"+(i+5)+".png").toString(), 351, 200 ,false , false);
			spinAttackL2[i] = new Image(ClassLoader.getSystemResource("design/bp2/spin/bp2_spin-0"+(i+2)+".png").toString(), 351, 200 ,false , false);
			spinAttackR2[i] = new Image(ClassLoader.getSystemResource("design/bp2/spin/bp2_spin-0"+(i+5)+".png").toString(), 351, 200 ,false , false);
			spinAttackL3[i] = new Image(ClassLoader.getSystemResource("design/bp3/spin/bp3_spin-0"+(i+2)+".png").toString(), 351, 200 ,false , false);
			spinAttackR3[i] = new Image(ClassLoader.getSystemResource("design/bp3/spin/bp3_spin-0"+(i+5)+".png").toString(), 351, 200 ,false , false);
		}
		
		for(int i=0;i<4;i++){
			selectMenu.add(new Image(ClassLoader.getSystemResource("design/mainmenu/mainmenu-0"+(i+1)+".png").toString()));
		}
		
		for(int i=1;i<=2;i++){
			quitMenu.add(new Image(ClassLoader.getSystemResource("design/quitmenu/quitmenu-0"+i+".png").toString()));
		}
		
		for(int i=1;i<=2;i++){
			gamePause.add(new Image(ClassLoader.getSystemResource("design/gamepause/gamepause-0"+i+".png").toString()));
		}
		
		for(int i=1;i<=2;i++){
			deadScene.add(new Image(ClassLoader.getSystemResource("design/deadscene/deadscene-0"+i+".png").toString()));
		}
	
		STAGE1.add(Images.blackTigerMotionL);
		STAGE1.add(Images.blackTigerMotionR);
		STAGE1.add(Images.blackTigerBasicAttackL);
		STAGE1.add(Images.blackTigerBasicAttackR);
		STAGE1.add(Images.jumpAttackL);
		STAGE1.add(Images.jumpAttackR);
		STAGE1.add(Images.spinAttackL);
		STAGE1.add(Images.spinAttackR);
		
		STAGE2.add(Images.blackTigerMotionL2);
		STAGE2.add(Images.blackTigerMotionR2);
		STAGE2.add(Images.blackTigerBasicAttackL2);
		STAGE2.add(Images.blackTigerBasicAttackR2);
		STAGE2.add(Images.jumpAttackL2);
		STAGE2.add(Images.jumpAttackR2);
		STAGE2.add(Images.spinAttackL2);
		STAGE2.add(Images.spinAttackR2);
		
		STAGE3.add(Images.blackTigerMotionL3);
		STAGE3.add(Images.blackTigerMotionR3);
		STAGE3.add(Images.blackTigerBasicAttackL3);
		STAGE3.add(Images.blackTigerBasicAttackR3);
		STAGE3.add(Images.jumpAttackL3);
		STAGE3.add(Images.jumpAttackR3);
		STAGE3.add(Images.spinAttackL3);
		STAGE3.add(Images.spinAttackR3);
	
		STAGETIGER.add(STAGE1);
		STAGETIGER.add(STAGE2);
		STAGETIGER.add(STAGE3);

	}
}
