package application;

import javafx.scene.media.AudioClip;

public class Audio {
	public static final AudioClip BGM = new AudioClip(ClassLoader.getSystemResource("sound/bgm-01 naruto.wav").toString());
	public static final AudioClip GETHIT = new AudioClip(ClassLoader.getSystemResource("sound/attack.wav").toString());
	public static final AudioClip SELECTMENU = new AudioClip(ClassLoader.getSystemResource("sound/selectmenu.wav").toString());
	public static final AudioClip MENU_BGM = new AudioClip(ClassLoader.getSystemResource("sound/bgm-03 menu.wav").toString());
	public static final AudioClip STARTGAME = new AudioClip(ClassLoader.getSystemResource("sound/startgame.wav").toString());
	public static final AudioClip RAGE = new AudioClip(ClassLoader.getSystemResource("sound/rage.wav").toString());
	public static final AudioClip DEAD = new AudioClip(ClassLoader.getSystemResource("sound/dead.wav").toString());
	//BlackPanther
	public static final AudioClip WALK = new AudioClip(ClassLoader.getSystemResource("design/bp1/walk/bp1_walk.wav").toString());
	public static final AudioClip ATTACK = new AudioClip(ClassLoader.getSystemResource("design/bp1/attack/bp1_attack-01.wav").toString());
	public static final AudioClip POUNCE = new AudioClip(ClassLoader.getSystemResource("design/bp1/jump/bp1_jump.wav").toString());
	public static final AudioClip SPIN = new AudioClip(ClassLoader.getSystemResource("design/bp1/spin/bp1_spin-01.wav").toString());
	public static final AudioClip SUPER_ATTACK = new AudioClip(ClassLoader.getSystemResource("design/bp2/attack/bp2_attack-01.wav").toString());
	
	//Enemy
	public static final AudioClip ENEMY_DEAD = new AudioClip(ClassLoader.getSystemResource("design/enemy1/enemy1dead.wav").toString());
	
	//Item
	public static final AudioClip EAT = new AudioClip(ClassLoader.getSystemResource("design/item/eat.wav").toString());
	public static final AudioClip DRINK = new AudioClip(ClassLoader.getSystemResource("design/item/drink.wav").toString());
	
	public static boolean HITDETECTED = false;
	public static AudioClip[] ListHIT =new AudioClip[20];
	public static boolean[] AudioAvailable = new boolean[20];


	
	static {
		for(int i =0; i<=10;i++){
			ListHIT[i] = GETHIT;
//			AudioAvailable
		}
	}
	
	public static void playBackGround() {
		Audio.BGM.setCycleCount(AudioClip.INDEFINITE);
		Audio.BGM.setVolume(0.2);
		Audio.BGM.play();
	}
	public static void playGetHit(int index) {
		Thread pa = new Thread(()-> {
			Audio.ListHIT[index].play();
			try {
				
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			Audio.GETHIT.stop();
		});
		pa.start();
		
	}

	public static void attackSound() {
		// TODO Auto-generated method stub
		Audio.ATTACK.play(0.4);
//		Thread p = new Thread(()->) {
//			
//		}
	}
}
