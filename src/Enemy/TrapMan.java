package Enemy;

import Constant.Images;

public class TrapMan extends BadHuman {
	
	public TrapMan() {
		this.setImage((Images.TRAPMANL)[0]);
		this.setImageL(Images.TRAPMANL);
		this.setImageR(Images.TRAPMANR);
		this.setImageList(Images.TRAPMANR);
	}
	
}
