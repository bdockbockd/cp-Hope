package Item;

import Constant.Audio;
import Constant.Images;
import Sprite.BlackPanther;

public class SuperPotion extends Item {
	
	private static final int duration = 1500; //15 sec
	
	public SuperPotion(double x, double y){
		super(x, y, Images.superPotion);
	}
	
	@Override
	public void itemUse(BlackPanther blackTiger) {
		System.out.println("SuperPotion!");
		Audio.EAT.play();
		Audio.RAGE.play();
	}
}
