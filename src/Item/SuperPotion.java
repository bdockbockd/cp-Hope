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
		if(BlackPanther.ISSUPER == true) {
			return;
		}
		Thread delay = new Thread(()->{
			int times = 0;
			BlackPanther.ISSUPER = true;
			while(true){
				try {
					Thread.sleep(1000);
					times+=1;
					if(times==15) {
						break;
					}
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			BlackPanther.ISSUPER = false;
		});
		delay.start();
		Audio.EAT.play();
		Audio.RAGE.play();
	}
}
