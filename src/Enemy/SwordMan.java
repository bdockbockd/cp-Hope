package Enemy;

import Constant.Images;

public class SwordMan extends BadHuman {
	
	public final static int SCORE = 2000;

	public SwordMan() {
		this.setImage((Images.SWORDMANR)[0]);
		this.setImageL(Images.SWORDMANL);
		this.setImageR(Images.SWORDMANR);
		this.setImageList(Images.SWORDMANL);
	}

	public void nextPosition(String direction) {
		if (this.getFace().equals("LEFT")) {
			this.setImage((this.getImageL())[0]);
		} else {
			this.setImage((this.getImageR())[0]);
		}
	}
}
