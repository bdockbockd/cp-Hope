package Item;

import Constant.Audio;
import Constant.Images;
import Sprite.BlackPanther;

public class SuperPotion extends Item {

	private static final int duration = 15; // 15 sec
	private int durationLeft = 15;

	public SuperPotion(double x, double y) {
		super(x, y, Images.SUPERPOTION, Images.SUPERPOTIOND);
	}

	@Override
	public void itemUse(BlackPanther blackTiger) {
		if (BlackPanther.ISSUPER == true) {
			this.durationLeft = -1;
		}
		Thread delay = new Thread(() -> {
			BlackPanther.ISSUPER = true;
			this.durationLeft = duration;
			while (true) {
				try {
					Thread.sleep(1000);
					this.durationLeft = this.durationLeft - 1;
					if (durationLeft == 0) {
						break;
					}
					if (durationLeft == -1) {
						this.durationLeft = 15;
					}

				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
			BlackPanther.ISSUPER = false;
		});
		delay.start();
		Audio.EAT.play();
		Audio.RAGE.play();
	}

	public int getDurationLeft() {
		return durationLeft;
	}

}
