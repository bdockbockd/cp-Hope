package Item;

import Constant.Audio;
import Constant.Images;
import Sprite.BlackPanther;

public class HealthPotion extends Item {
	
	private static final int healFactor = 500;
	
	public HealthPotion(double x, double y){
		super(x, y, Images.healthPotion, Images.healthPotionD, false);
	}
	public HealthPotion() {
		super(0, 0, Images.healthPotion, Images.healthPotionD, false);
	}
	
	@Override
	public void itemUse(BlackPanther blackTiger) {
		System.out.println("HealthPotion!");
		blackTiger.heal(healFactor);
		Audio.EAT.play();
	}
}
