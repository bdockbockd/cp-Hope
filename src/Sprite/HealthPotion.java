package Sprite;

import application.Audio;
import application.Images;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class HealthPotion extends Item {
	
	private static final int healFactor = 500;
	
	public HealthPotion(double x, double y){
		super(x, y, Images.healthPotion);
	}
	public HealthPotion() {
		super(0, 0, Images.healthPotion);
	}
	
	@Override
	public void itemUse(BlackTiger blackTiger) {
		System.out.println("HealthPotion!");
		blackTiger.heal(healFactor);
		Audio.EAT.play();
	}
}
