package Sprite;

import application.Audio;
import application.Images;
import javafx.scene.image.Image;

public class Meat extends Item {
	private static final int healFactor = 50;
	
	public Meat(double x, double y){
		super(x, y, Images.meat);
	}
	
	public Meat() {
		super(0, 0, Images.meat);
	}

	@Override
	public void itemUse(BlackTiger blackTiger) {
		System.out.println("Meat!");
		blackTiger.heal(healFactor);
		Audio.EAT.play();
	}
}
