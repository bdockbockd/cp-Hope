package Item;

import Constant.Audio;
import Constant.Images;
import Sprite.BlackPanther;

public class Meat extends Item {
	private static final int healFactor = 50;
	
	public Meat(double x, double y){
		super(x, y, Images.MEAT, Images.MEATD);
	}

	public Meat() {
		super(0, 0, Images.MEAT, Images.MEATD);
	}

	@Override
	public void itemUse(BlackPanther blackTiger) {
		blackTiger.heal(healFactor);
		Audio.EAT.play();
	}
}
