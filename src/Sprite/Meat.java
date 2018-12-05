package Sprite;

import application.Images;
import javafx.scene.image.Image;

public class Meat extends Item {
	private static int healFactor = 50;
	
	public Meat(double x, double y){
		super(x, y, Images.meat);
	}
	
	public Meat() {
		super(0, 0, Images.meat);
	}
	
	public void itemUse() {
		
	}
	@Override
	public void update(double time) {
		// TODO Auto-generated method stub
		
	}
}
