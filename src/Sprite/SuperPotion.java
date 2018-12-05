package Sprite;

import application.Audio;
import application.Images;
import javafx.scene.image.Image;

public class SuperPotion extends Item {
	
	private static final int duration = 1500; //15 sec
	
	public SuperPotion(double x, double y){
		super(x, y, Images.superPotion);
	}
	
	@Override
	public void itemUse(BlackTiger blackTiger) {
		System.out.println("SuperPotion!");
		application.Audio.RAGE.play();
		Audio.DRINK.play();
	}
}
