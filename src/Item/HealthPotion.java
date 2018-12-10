package Item;

import Constant.Audio;
import Constant.Images;
import Sprite.BlackPanther;

public class HealthPotion extends Item {

	private static final int HEALFACTOR = 200;

	public HealthPotion(double x, double y) {
		super(x, y, Images.HEALTHPOTION, Images.HEALTHPOTIOND);
	}

	public HealthPotion() {
		super(0, 0, Images.HEALTHPOTION, Images.HEALTHPOTIOND);
	}

	@Override
	public void itemUse(BlackPanther blackPanther) {
		blackPanther.heal(HEALFACTOR);
		Audio.EAT.play();
	}
}
