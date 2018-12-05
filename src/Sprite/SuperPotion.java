package Sprite;

import application.Images;
import javafx.scene.image.Image;

public class SuperPotion extends Item {
	private static int duration = 1500; //15 sec
	
	public SuperPotion(double x, double y){
		super(x, y, Images.superPotion);
	}
	public void itemUse() {
		
	}
	@Override
	public void update(double time) {
		// TODO Auto-generated method stub
		
	}
}
