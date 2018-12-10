package Constant;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class Images {
	
	public static final Image[] BLACKTIGERMOTIONL = new Image[3];
	public static final Image[] BLACKTIGERMOTIONR = new Image[3];
	public static final Image[] BLACKTIGERBASICATTACKL = new Image[3];
	public static final Image[] BLACKTIGERBASICATTACKR = new Image[3];

	public static final Image[] BLACKTIGERMOTIONL2 = new Image[3];
	public static final Image[] BLACKTIGERMOTIONR2 = new Image[3];
	public static final Image[] BLACKTIGERBASICATTACKL2 = new Image[3];
	public static final Image[] BLACKTIGERBASICATTACKR2 = new Image[3];

	public static final Image[] BLACKTIGERMOTIONL3 = new Image[3];
	public static final Image[] BLACKTIGERMOTIONR3 = new Image[3];
	public static final Image[] BLACKTIGERBASICATTACKL3 = new Image[3];
	public static final Image[] BLACKTIGERBASICATTACKR3 = new Image[3];

	public static final Image[] JUMPATTACKL = new Image[1];
	public static final Image[] jumpAttackR = new Image[1];
	public static final Image[] JUMPATTACKL2 = new Image[1];
	public static final Image[] JUMPATTACKR2 = new Image[1];
	public static final Image[] JUMPATTACKL3 = new Image[1];
	public static final Image[] JUMPATTACKR3 = new Image[1];

	public static final Image[] SPINATTACKL = new Image[2];
	public static final Image[] SPINATTACKR = new Image[2];
	public static final Image[] SPINATTACKL2 = new Image[2];
	public static final Image[] SPINATTACKR2 = new Image[2];
	public static final Image[] SPINATTACKL3 = new Image[2];
	public static final Image[] SPINATTACKR3 = new Image[2];

	public static final Image[] HUMANMOTIONL = new Image[2]; // 13
	public static final Image[] HUMANMOTIONR = new Image[2];// 24
	public static final Image[] SWORDMANL = new Image[2]; // 24
	public static final Image[] SWORDMANR = new Image[2]; // 13
	public static final Image[] TRAPMANL = new Image[2];// 24
	public static final Image[] TRAPMANR = new Image[2];// 13
	public static final Image[] TRAPITEM = new Image[2]; //
	public static final Image[] GUNMANL = new Image[2];// 2
	public static final Image[] GUNMANR = new Image[2];// 1
	public static final Image[] BULLET = new Image[2];

	public static final Image[] STAGEMAP = new Image[3];

	public static final ArrayList<ArrayList<Image[]>> STAGETIGER = new ArrayList<ArrayList<Image[]>>();
	public static final ArrayList<Image[]> STAGE1 = new ArrayList<Image[]>();
	public static final ArrayList<Image[]> STAGE2 = new ArrayList<Image[]>();
	public static final ArrayList<Image[]> STAGE3 = new ArrayList<Image[]>();

	public static final Image ENEMYTOMB = new Image(ClassLoader.getSystemResource("design/rip/rip.png").toString(), 70,
			98, false, false);

	public static final Image HEALTHPOTION = new Image(
			ClassLoader.getSystemResource("design/item/item-01.png").toString(), 65, 49, false, false);
	public static final Image HEALTHPOTIOND = new Image(
			ClassLoader.getSystemResource("design/item/item-04.png").toString(), 65, 49, false, false);
	public static final Image SUPERPOTION = new Image(
			ClassLoader.getSystemResource("design/item/item-02.png").toString(), 65, 49, false, false);
	public static final Image SUPERPOTIOND = new Image(
			ClassLoader.getSystemResource("design/item/item-05.png").toString(), 65, 49, false, false);
	public static final Image MEAT = new Image(ClassLoader.getSystemResource("design/item/item-03.png").toString(), 65,
			49, false, false);
	public static final Image MEATD = new Image(ClassLoader.getSystemResource("design/item/item-06.png").toString(), 65,
			49, false, false);

	public static final Image PLAYERNAMEMENU = new Image(
			ClassLoader.getSystemResource("design/gameinputname/gameinputname.png").toString());
	public static final Image HALLOFFAMEBG = new Image(
			ClassLoader.getSystemResource("design/halloffame/halloffame.png").toString());
	public static final Image HOWTOPLAYGAME = new Image(
			ClassLoader.getSystemResource("design/howtoplay/howtoplay.png").toString());
	public static final Image STATUSBAR = new Image(
			ClassLoader.getSystemResource("design/statusbar/gamestatusbar.png").toString());
	public static final ArrayList<Image> SELECTMENU = new ArrayList<Image>();
	public static final ArrayList<Image> QUITMENU = new ArrayList<Image>();
	public static final ArrayList<Image> GAMEPAUSE = new ArrayList<Image>();
	public static final ArrayList<Image> DEADSCENE = new ArrayList<Image>();

	static {
		for (int i = 0; i < STAGEMAP.length; i++) {
			STAGEMAP[i] = new Image(ClassLoader.getSystemResource("design/map/map-0" + (i + 1) + ".png").toString(),
					1250, 800, false, false);
		}
		for (int i = 0; i < HUMANMOTIONL.length; i++) {
			HUMANMOTIONL[i] = new Image(
					ClassLoader.getSystemResource("design/enemy1/enemy1-0" + (i + 1) + ".png").toString(), 70, 98,
					false, false);
			HUMANMOTIONR[i] = new Image(
					ClassLoader.getSystemResource("design/enemy1/enemy1-0" + (i + 3) + ".png").toString(), 70, 98,
					false, false);
			SWORDMANL[i] = new Image(
					ClassLoader.getSystemResource("design/enemy2/enemy2-0" + (i + 1) + ".png").toString(), 70, 99,
					false, false);
			SWORDMANR[i] = new Image(
					ClassLoader.getSystemResource("design/enemy2/enemy2-0" + (i + 3) + ".png").toString(), 70, 99,
					false, false);
			TRAPMANL[i] = new Image(
					ClassLoader.getSystemResource("design/enemy3/enemy3-0" + (i + 1) + ".png").toString(), 70, 98,
					false, false);
			TRAPMANR[i] = new Image(
					ClassLoader.getSystemResource("design/enemy3/enemy3-0" + (i + 3) + ".png").toString(), 70, 98,
					false, false);
			TRAPITEM[i] = new Image(
					ClassLoader.getSystemResource("design/enemy3/enemy3-0" + (i + 5) + ".png").toString(), 58, 37,
					false, false);
			GUNMANL[i] = new Image(ClassLoader.getSystemResource("design/enemy4/enemy4-01.png").toString(), 120, 135,
					false, false);
			GUNMANR[i] = new Image(ClassLoader.getSystemResource("design/enemy4/enemy4-02.png").toString(), 120, 135,
					false, false);
			BULLET[i] = new Image(ClassLoader.getSystemResource("design/enemy4/bullet-0" + (i + 1) + ".png").toString(),
					68, 24, false, false);
		}

		for (int i = 0; i < BLACKTIGERBASICATTACKL.length; i++) {
			BLACKTIGERBASICATTACKL[i] = new Image(
					ClassLoader.getSystemResource("design/bp1/attack/bp1_attack-0" + (i + 1) + ".png").toString(), 351,
					200, false, false);
			BLACKTIGERBASICATTACKR[i] = new Image(
					ClassLoader.getSystemResource("design/bp1/attack/bp1_attack-0" + (i + 4) + ".png").toString(), 351,
					200, false, false);
			BLACKTIGERBASICATTACKL2[i] = new Image(
					ClassLoader.getSystemResource("design/bp2/attack/bp2_attack-0" + (i + 1) + ".png").toString(), 351,
					200, false, false);
			BLACKTIGERBASICATTACKR2[i] = new Image(
					ClassLoader.getSystemResource("design/bp2/attack/bp2_attack-0" + (i + 4) + ".png").toString(), 351,
					200, false, false);
			BLACKTIGERBASICATTACKL3[i] = new Image(
					ClassLoader.getSystemResource("design/bp3/attack/bp3_attack-0" + (i + 1) + ".png").toString(), 351,
					200, false, false);
			BLACKTIGERBASICATTACKR3[i] = new Image(
					ClassLoader.getSystemResource("design/bp3/attack/bp3_attack-0" + (i + 4) + ".png").toString(), 351,
					200, false, false);
		}
		for (int i = 0; i < BLACKTIGERMOTIONL.length; i++) {
			BLACKTIGERMOTIONL[i] = new Image(
					ClassLoader.getSystemResource("design/bp1/walk/bp1_walk-0" + (i + 1) + ".png").toString(), 351, 200,
					false, false);
			BLACKTIGERMOTIONR[i] = new Image(
					ClassLoader.getSystemResource("design/bp1/walk/bp1_walk-0" + (i + 4) + ".png").toString(), 351, 200,
					false, false);
			BLACKTIGERMOTIONL2[i] = new Image(
					ClassLoader.getSystemResource("design/bp2/walk/bp2_walk-0" + (i + 1) + ".png").toString(), 351, 200,
					false, false);
			BLACKTIGERMOTIONR2[i] = new Image(
					ClassLoader.getSystemResource("design/bp2/walk/bp2_walk-0" + (i + 4) + ".png").toString(), 351, 200,
					false, false);
			BLACKTIGERMOTIONL3[i] = new Image(
					ClassLoader.getSystemResource("design/bp3/walk/bp3_walk-0" + (i + 1) + ".png").toString(), 351, 200,
					false, false);
			BLACKTIGERMOTIONR3[i] = new Image(
					ClassLoader.getSystemResource("design/bp3/walk/bp3_walk-0" + (i + 4) + ".png").toString(), 351, 200,
					false, false);
		}

		for (int i = 0; i < JUMPATTACKL.length; i++) {
			JUMPATTACKL[i] = new Image(
					ClassLoader.getSystemResource("design/bp1/jump/bp1_jump-0" + (2) + ".png").toString(), 351, 200,
					false, false);
			jumpAttackR[i] = new Image(
					ClassLoader.getSystemResource("design/bp1/jump/bp1_jump-0" + (4) + ".png").toString(), 351, 200,
					false, false);
			JUMPATTACKL2[i] = new Image(
					ClassLoader.getSystemResource("design/bp2/jump/bp2_jump-0" + (2) + ".png").toString(), 351, 200,
					false, false);
			JUMPATTACKR2[i] = new Image(
					ClassLoader.getSystemResource("design/bp2/jump/bp2_jump-0" + (4) + ".png").toString(), 351, 200,
					false, false);
			JUMPATTACKL3[i] = new Image(
					ClassLoader.getSystemResource("design/bp3/jump/bp3_jump-0" + (2) + ".png").toString(), 351, 200,
					false, false);
			JUMPATTACKR3[i] = new Image(
					ClassLoader.getSystemResource("design/bp3/jump/bp3_jump-0" + (4) + ".png").toString(), 351, 200,
					false, false);
		}

		for (int i = 0; i < SPINATTACKL.length; i++) {
			SPINATTACKL[i] = new Image(
					ClassLoader.getSystemResource("design/bp1/spin/bp1_spin-0" + (i + 2) + ".png").toString(), 351, 200,
					false, false);
			SPINATTACKR[i] = new Image(
					ClassLoader.getSystemResource("design/bp1/spin/bp1_spin-0" + (i + 5) + ".png").toString(), 351, 200,
					false, false);
			SPINATTACKL2[i] = new Image(
					ClassLoader.getSystemResource("design/bp2/spin/bp2_spin-0" + (i + 2) + ".png").toString(), 351, 200,
					false, false);
			SPINATTACKR2[i] = new Image(
					ClassLoader.getSystemResource("design/bp2/spin/bp2_spin-0" + (i + 5) + ".png").toString(), 351, 200,
					false, false);
			SPINATTACKL3[i] = new Image(
					ClassLoader.getSystemResource("design/bp3/spin/bp3_spin-0" + (i + 2) + ".png").toString(), 351, 200,
					false, false);
			SPINATTACKR3[i] = new Image(
					ClassLoader.getSystemResource("design/bp3/spin/bp3_spin-0" + (i + 5) + ".png").toString(), 351, 200,
					false, false);
		}

		for (int i = 0; i < 4; i++) {
			SELECTMENU.add(new Image(
					ClassLoader.getSystemResource("design/mainmenu/mainmenu-0" + (i + 1) + ".png").toString()));
		}

		for (int i = 1; i <= 2; i++) {
			QUITMENU.add(
					new Image(ClassLoader.getSystemResource("design/quitmenu/quitmenu-0" + i + ".png").toString()));
		}

		for (int i = 1; i <= 2; i++) {
			GAMEPAUSE.add(
					new Image(ClassLoader.getSystemResource("design/gamepause/gamepause-0" + i + ".png").toString()));
		}

		for (int i = 1; i <= 2; i++) {
			DEADSCENE.add(
					new Image(ClassLoader.getSystemResource("design/deadscene/deadscene-0" + i + ".png").toString()));
		}

		STAGE1.add(Images.BLACKTIGERMOTIONL);
		STAGE1.add(Images.BLACKTIGERMOTIONR);
		STAGE1.add(Images.BLACKTIGERBASICATTACKL);
		STAGE1.add(Images.BLACKTIGERBASICATTACKR);
		STAGE1.add(Images.JUMPATTACKL);
		STAGE1.add(Images.jumpAttackR);
		STAGE1.add(Images.SPINATTACKL);
		STAGE1.add(Images.SPINATTACKR);

		STAGE2.add(Images.BLACKTIGERMOTIONL2);
		STAGE2.add(Images.BLACKTIGERMOTIONR2);
		STAGE2.add(Images.BLACKTIGERBASICATTACKL2);
		STAGE2.add(Images.BLACKTIGERBASICATTACKR2);
		STAGE2.add(Images.JUMPATTACKL2);
		STAGE2.add(Images.JUMPATTACKR2);
		STAGE2.add(Images.SPINATTACKL2);
		STAGE2.add(Images.SPINATTACKR2);

		STAGE3.add(Images.BLACKTIGERMOTIONL3);
		STAGE3.add(Images.BLACKTIGERMOTIONR3);
		STAGE3.add(Images.BLACKTIGERBASICATTACKL3);
		STAGE3.add(Images.BLACKTIGERBASICATTACKR3);
		STAGE3.add(Images.JUMPATTACKL3);
		STAGE3.add(Images.JUMPATTACKR3);
		STAGE3.add(Images.SPINATTACKL3);
		STAGE3.add(Images.SPINATTACKR3);

		STAGETIGER.add(STAGE1);
		STAGETIGER.add(STAGE2);
		STAGETIGER.add(STAGE3);

	}
}
