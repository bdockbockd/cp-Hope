package Constant;

import javafx.scene.media.AudioClip;

public class Audio {

	public static final AudioClip GAME_BGM = new AudioClip(
			ClassLoader.getSystemResource("sound/bgm-01 naruto.wav").toString());
	public static final AudioClip SELECTMENU = new AudioClip(
			ClassLoader.getSystemResource("sound/selectmenu.wav").toString());
	public static final AudioClip CHOOSEMENU = new AudioClip(
			ClassLoader.getSystemResource("sound/choosemenu.wav").toString());
	public static final AudioClip MENU_BGM = new AudioClip(
			ClassLoader.getSystemResource("sound/bgm-03 menu.wav").toString());
	public static final AudioClip STARTGAME = new AudioClip(
			ClassLoader.getSystemResource("sound/startgame.wav").toString());
	
	public static final AudioClip RAGE = new AudioClip(ClassLoader.getSystemResource("sound/rage.wav").toString());
	public static final AudioClip DEAD = new AudioClip(ClassLoader.getSystemResource("sound/dead.wav").toString());
	public static final AudioClip STUN = new AudioClip(ClassLoader.getSystemResource("sound/stun.wav").toString());
	public static final AudioClip GETHIT = new AudioClip(ClassLoader.getSystemResource("sound/attack.wav").toString());
	public static final AudioClip HEARTBEAT = new AudioClip(ClassLoader.getSystemResource("sound/heartbeat.wav").toString());
	
	// BlackPanther
	public static final AudioClip WALK = new AudioClip(
			ClassLoader.getSystemResource("design/bp1/walk/bp1_walk.wav").toString());
	public static final AudioClip ATTACK = new AudioClip(
			ClassLoader.getSystemResource("design/bp1/attack/bp1_attack-01.wav").toString());
	public static final AudioClip POUNCE = new AudioClip(
			ClassLoader.getSystemResource("design/bp1/jump/bp1_jump.wav").toString());
	public static final AudioClip SPIN = new AudioClip(
			ClassLoader.getSystemResource("design/bp1/spin/bp1_spin-01.wav").toString());
	public static final AudioClip SUPER_ATTACK = new AudioClip(
			ClassLoader.getSystemResource("design/bp2/attack/bp2_attack-01.wav").toString());

	// Enemy
	public static final AudioClip ENEMY_DEAD = new AudioClip(
			ClassLoader.getSystemResource("design/enemy1/enemy1dead.wav").toString());
	public static final AudioClip ENEMY_FIRE = new AudioClip(
			ClassLoader.getSystemResource("design/enemy4/enemy4.wav").toString());
	public static final AudioClip ENEMY_TRAP = new AudioClip(
			ClassLoader.getSystemResource("design/enemy3/enemy3.wav").toString());

	// Item
	public static final AudioClip EAT = new AudioClip(ClassLoader.getSystemResource("design/item/eat.wav").toString());
	public static final AudioClip DRINK = new AudioClip(ClassLoader.getSystemResource("design/item/drink.wav").toString());

	public static boolean HITDETECTED = false;
	public static AudioClip[] ListHIT = new AudioClip[20];
	public static boolean[] AudioAvailable = new boolean[20];

	static {
		Audio.SELECTMENU.setVolume(0.2);
		Audio.GAME_BGM.setCycleCount(AudioClip.INDEFINITE);
		Audio.GAME_BGM.setVolume(0.2);
		Audio.MENU_BGM.setCycleCount(AudioClip.INDEFINITE);// Audio.GAME_BGM.stop();
		Audio.MENU_BGM.setVolume(0.2);
		for (int i = 0; i <= 10; i++) {
			ListHIT[i] = GETHIT;
		}
	}

	public static void playGetHit(int index) {
		Thread pa = new Thread(() -> {
			Audio.ListHIT[index].play();
			try {

				Thread.sleep(300);
			} catch (InterruptedException e) {
				
				e.printStackTrace();

			}
			Audio.GETHIT.stop();
		});
		pa.start();

	}

	public static void attackSound() {
		
		Audio.ATTACK.play(0.4);
	}

	public static void spinSound() {
		
		Audio.SPIN.play(0.4);
	}

	public static void pounceSound() {
		Audio.POUNCE.play(0.4);
	}

	public static void stop() {
		Audio.HEARTBEAT.stop();
		Audio.ENEMY_FIRE.stop();
		Audio.ENEMY_TRAP.stop();
	}

}
