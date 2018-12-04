package application;

import javafx.scene.media.AudioClip;

public class Music {
	public static AudioClip BGM = new AudioClip(ClassLoader.getSystemResource("resources/sound/bgm-01 naruto.wav").toString());
	public static AudioClip GETHIT = new AudioClip(ClassLoader.getSystemResource("resources/sound/attack.wav").toString());
	public static AudioClip[] ListHIT =new AudioClip[20];
	public static boolean[] AudioAvailable = new boolean[20];

	
	static {
		for(int i =0; i<=10;i++){
			ListHIT[i] = GETHIT;
//			AudioAvailable
		}
	}
	
	public static void playBackGround() {
		Music.BGM.setCycleCount(AudioClip.INDEFINITE);
		Music.BGM.play();
		Music.BGM.setVolume(0.1);
	}
	
	public static void playGetHit(int index) {
		Thread pa = new Thread(()-> {
			Music.ListHIT[index].play();
			try {
				
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			Music.GETHIT.stop();
		});
		pa.start();
		
	}
}
