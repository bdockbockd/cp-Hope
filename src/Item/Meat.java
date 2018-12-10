package Item;

import Constant.Audio;
import Constant.Images;
import Sprite.BlackPanther;

public class Meat extends Item {
	private static final int healFactor = 50;

	public Meat(double x, double y) {
		super(x, y, Images.meat, Images.meatD);
	}

	public Meat() {
		super(0, 0, Images.meat, Images.meatD);
	}

	@Override
	public void itemUse(BlackPanther blackTiger) {
		System.out.println("Meat!");
		blackTiger.heal(healFactor);
		Audio.EAT.play();
	}
}
